package ru.skillbox.diplom.group25.microservice.dialog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.diplom.group25.library.core.configuration.TechnicalUserConfig;
import ru.skillbox.diplom.group25.library.core.util.TokenUtil;
import ru.skillbox.diplom.group25.microservice.account.client.AccountFeignClient;
import ru.skillbox.diplom.group25.microservice.account.model.AccountDto;
import ru.skillbox.diplom.group25.microservice.dialog.dto.DialogDto;
import ru.skillbox.diplom.group25.microservice.dialog.dto.DialogMessage;
import ru.skillbox.diplom.group25.microservice.dialog.dto.MessageDto;
import ru.skillbox.diplom.group25.microservice.dialog.dto.MessageShortDto;
import ru.skillbox.diplom.group25.microservice.dialog.dto.SetStatusMessageReadDto;
import ru.skillbox.diplom.group25.microservice.dialog.dto.UnreadCountDto;
import ru.skillbox.diplom.group25.microservice.dialog.dto.response.GetDialogsRs;
import ru.skillbox.diplom.group25.microservice.dialog.dto.response.GetMessagesRs;
import ru.skillbox.diplom.group25.microservice.dialog.dto.response.SetStatusMessageReadRs;
import ru.skillbox.diplom.group25.microservice.dialog.dto.response.UnreadCountRs;
import ru.skillbox.diplom.group25.microservice.dialog.mapper.DialogMapper;
import ru.skillbox.diplom.group25.microservice.dialog.mapper.MessageMapper;
import ru.skillbox.diplom.group25.microservice.dialog.model.DialogEntity;
import ru.skillbox.diplom.group25.microservice.dialog.model.MessageEntity;
import ru.skillbox.diplom.group25.microservice.dialog.repository.DialogRepository;
import ru.skillbox.diplom.group25.microservice.dialog.repository.MessageRepository;

/**
 * DialogService
 *
 * @author alex90bar
 */

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DialogService {

  private final MessageRepository messageRepository;
  private final DialogRepository dialogRepository;
  private final MessageMapper messageMapper;
  private final DialogMapper dialogMapper;
  private final AccountFeignClient feignClient;
  private final TechnicalUserConfig technicalUserConfig;
  private final ObjectMapper objectMapper;

  /**
   * ?????????? ?????????????????? ?????????????????? ??????????????
   */
  public GetMessagesRs getAllMessages(Long companionId, Integer offset, Integer itemPerPage) {
    log.info("getAllMessages begins, companionId: {}", companionId);

    Long userId = TokenUtil.getJwtInfo().getId();

    DialogEntity dialog = dialogRepository.findByAuthorIdAndRecipientId(userId, companionId);

    if (dialog == null) {
      dialog = dialogRepository.findByAuthorIdAndRecipientId(companionId, userId);

      //???????? ???????????? ???? ???????????? ?? ???? = ?????????????? ?????????? ????????????
      if (dialog == null) {
        dialog = new DialogEntity();
        dialog.setAuthorId(userId);
        dialog.setRecipientId(companionId);
        dialog.setUnreadCountRecipient(0L);
        dialog.setUnreadCountAuthor(0L);
        dialogRepository.save(dialog);

        log.info("New dialog created: {}", dialog);

        GetMessagesRs response = new GetMessagesRs();
        response.setData(new ArrayList<>());
        response.setTotal(0);
        response.setOffset(offset);
        response.setPerPage(itemPerPage);
        response.setTimestamp(ZonedDateTime.now().toEpochSecond());

        log.info("Returning response: {}", response);
        return response;
      }
    }

    GetMessagesRs response = new GetMessagesRs();
    PageRequest request = PageRequest.of(offset, itemPerPage);
    final Long[] readCount = {0L};
    List<MessageShortDto> data = messageRepository.findMessageEntitiesByDialog_IdOrderByTimeDesc(dialog.getId(), request).map(entity -> {

      //???????????? ???????????? ?????????????????? ???? ??????????????????????, ???????? ?????????? ?????????????????? ???? ?????????????? ????????
      if (entity.getReadStatus().equals("SENT") && !entity.getAuthorId().equals(userId)) {
        entity.setReadStatus("READ");
        readCount[0]++;
      }

      return messageMapper.toShortDto(entity);

    }).getContent();

    //?????????????????????????? ???????????????????? ?????????????????????????? ?????????????????? ??????????????
    log.info("Messages changed from unread to read: {}", readCount[0]);

    if (dialog.getAuthorId().equals(userId)){
      dialog.setUnreadCountAuthor(dialog.getUnreadCountAuthor() - readCount[0]);
    } else {
      dialog.setUnreadCountRecipient(dialog.getUnreadCountRecipient() - readCount[0]);
    }

    response.setData(data);
    response.setTotal(data.size());
    response.setOffset(offset);
    response.setPerPage(itemPerPage);
    response.setTimestamp(ZonedDateTime.now().toEpochSecond());

    log.info("Returning response: {}", response);
    return response;
  }

  /**
   * ?????????? ?????????????????? ???????????????????? ???????? ?????????????????????????? ?????????????????? ????????????????
   */
  public UnreadCountRs getUnreadMessageCount() {
    log.info("getUnreadMessageCount begins");

    Long userId = TokenUtil.getJwtInfo().getId();

    UnreadCountRs response = new UnreadCountRs();

    //?????????????? ?????? ?????????????? ????????????????????????, ?????????????????? ???????????????????? ??????????????????????????
    Long unreadCount = dialogRepository.findAllByAuthorIdOrRecipientId(userId, userId).stream()
        .map(dialog -> dialog.getAuthorId().equals(userId) ? dialog.getUnreadCountAuthor() : dialog.getUnreadCountRecipient())
        .collect(Collectors.summarizingLong(Long::longValue)).getSum();

    response.setData(new UnreadCountDto(unreadCount));
    response.setTimestamp(ZonedDateTime.now().toEpochSecond());

    log.info("Returning response: {}", response);
    return response;
  }

  /**
   * ?????????? ?????????????????? ???????? ???????????????? ????????????????????????
   */
  public GetDialogsRs getAllDialogs(Integer offset, Integer itemPerPage) {
    log.info("getAllDialogs begins");

    Long userId = TokenUtil.getJwtInfo().getId();

    GetDialogsRs response = new GetDialogsRs();

    PageRequest request = PageRequest.of(offset, itemPerPage);

    List<DialogDto> data = dialogRepository.findAllByAuthorIdOrRecipientIdOrderByLastMessageDesc(userId, userId, request).map(dialog -> {
      Long conversationPartner = dialog.getAuthorId().equals(userId) ? dialog.getRecipientId() : dialog.getAuthorId();

      PageRequest pageRequest = PageRequest.of(0, 100);
      final AccountDto[] accountDto = new AccountDto[1];

      technicalUserConfig.executeByTechnicalUser(() -> accountDto[0] = feignClient.getAllAccounts(pageRequest).getBody().stream()
          .filter(acc -> acc.getId().equals(conversationPartner))
          .findFirst()
          .orElse(new AccountDto()));

      //???????? ???????????? ???????????? - ?????????????????? ?????????????????? ???? ????????????????????
      MessageEntity entity = dialog.getMessages().stream().max(Comparator.comparing(MessageEntity::getId)).orElse(null);
      if (entity == null) {
        return dialogMapper.toDto(dialog, accountDto[0], new MessageDto(), 0L);
      }

      MessageDto lastMessage = messageMapper.toDto(entity);
      Long recipientId = lastMessage.getAuthorId().equals(dialog.getAuthorId()) ? dialog.getRecipientId() : dialog.getAuthorId();
      lastMessage.setRecipientId(recipientId);

      Long unreadCount = dialog.getAuthorId().equals(userId) ? dialog.getUnreadCountAuthor() : dialog.getUnreadCountRecipient();

      return dialogMapper.toDto(dialog, accountDto[0], lastMessage, unreadCount);
    }).getContent();

    response.setTotal(data.size());
    response.setData(data);
    response.setCurrentUserId(userId);
    response.setOffset(offset);
    response.setPerPage(itemPerPage);
    response.setTimestamp(ZonedDateTime.now().toEpochSecond());
    return response;
  }

  /**
   * ?????????? ?????? ???????????????? ?????????????? ?????????????????? ?? ??????????????????????
   */
  public SetStatusMessageReadRs setStatusMessageRead(Long companionId) {
    log.info("setStatusMessageRead begins");
    getAllMessages(companionId, 0, 100);

    log.info("setStatusMessageRead ends");
    SetStatusMessageReadRs response = new SetStatusMessageReadRs();
    response.setTimestamp(ZonedDateTime.now().toEpochSecond());
    response.setData(new SetStatusMessageReadDto("????"));
    return response;
  }

  /**
   * ?????????? ???????????????????? ??????????????????, ?????????????????????? ???? kafka ???? microservice-streaming
   */
  public void saveMessage(JsonNode message) {
    log.info("saveMessage begins");

    DialogMessage dialogMessage = mapJsonToDialogMessage(message);

    if (dialogMessage != null){

      //?????????????? ?????????????????????? ???????????? ?? ????
      DialogEntity dialog = dialogRepository
          .findByAuthorIdAndRecipientId(dialogMessage.getData().getAuthorId(), dialogMessage.getData().getRecipientId());

      if (dialog == null) {
        dialog = dialogRepository.findByAuthorIdAndRecipientId(dialogMessage.getData().getRecipientId(), dialogMessage.getData().getAuthorId());

        if (dialog == null) {
          log.error("Error while finding dialog");
          return;

        }
      }

      log.info("Dialog founded: {}", dialog);

      //?????????????????????????? ???????????????????? ?????????????????????????? ?? ?????????????????????????? ?????????????????? ?????????????????? ?????? ??????????????
      if (dialogMessage.getData().getAuthorId().equals(dialog.getAuthorId())) {
        dialog.setUnreadCountRecipient(dialog.getUnreadCountRecipient() + 1);
      } else {
        dialog.setUnreadCountAuthor(dialog.getUnreadCountAuthor() + 1);
      }

      dialog.setLastMessage(messageRepository.save(messageMapper.toEntity(dialogMessage.getData(), dialog)).getId());
    }

    log.info("saveMessage ends");
  }

  /**
   * ?????????? ???????????????????????????? ?????????????????? ???? JsonNode ?? ???????????? DialogMessage
   */
  public DialogMessage mapJsonToDialogMessage(JsonNode message) {

    try {
      return objectMapper.treeToValue(message, DialogMessage.class);
    } catch (JsonProcessingException e) {
      log.error("Error reading message: {}", e.getMessage());
      return null;
    }
  }


}


