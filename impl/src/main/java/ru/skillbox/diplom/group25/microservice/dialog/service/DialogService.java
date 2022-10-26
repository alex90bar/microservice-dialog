package ru.skillbox.diplom.group25.microservice.dialog.service;

import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.diplom.group25.library.core.util.TokenUtil;
import ru.skillbox.diplom.group25.microservice.account.model.AccountDto;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.DialogDto;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.MessageDto;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.MessageShortDto;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.response.GetDialogsRs;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.response.GetMessagesRs;
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

  public GetMessagesRs getAllMessages(Long interlocutorId, Integer offset, Integer itemPerPage) {
    log.info("getAllMessages begins, interlocutorId: {}", interlocutorId);

    Long userId = TokenUtil.getJwtInfo().getId();

    DialogEntity dialog = dialogRepository.findByAuthorIdAndRecipientId(userId, interlocutorId);

    if (dialog == null) {
      dialog = dialogRepository.findByAuthorIdAndRecipientId(interlocutorId, userId);

      if (dialog == null) {
        return new GetMessagesRs();

      }
    }

    GetMessagesRs response = new GetMessagesRs();
    PageRequest request = PageRequest.of(offset, itemPerPage);
    List<MessageShortDto> data = messageRepository.findMessageEntitiesByDialog_IdOrderByTimeDesc(dialog.getId(), request).map(messageMapper::toShortDto).getContent();

    response.setData(data);
    response.setTotal(data.size());
    response.setOffset(offset);
    response.setPerPage(itemPerPage);
    return response;
  }

  public GetDialogsRs getAllDialogs(Integer offset, Integer itemPerPage) {
    log.info("getAllDialogs begins");

    Long userId = TokenUtil.getJwtInfo().getId();

    GetDialogsRs response = new GetDialogsRs();

    PageRequest request = PageRequest.of(offset, itemPerPage);

    List<DialogDto> data = dialogRepository.findAllByAuthorIdOrRecipientIdOrderByLastMessageDesc(userId, userId, request).map(dialog -> {
//      Long conversationPartner = dialog.getAuthorId().equals(userId) ? dialog.getRecipientId() : dialog.getAuthorId();
      MessageDto lastMessage = messageMapper.toDto(dialog.getMessages().stream().max(Comparator.comparing(MessageEntity::getId)).orElse(new MessageEntity()));
      Long recipientId = lastMessage.getAuthorId().equals(dialog.getAuthorId()) ? dialog.getRecipientId() : dialog.getAuthorId();
      lastMessage.setRecipientId(recipientId);
      AccountDto conversationPartner = new AccountDto();
      return dialogMapper.toDto(dialog, conversationPartner, lastMessage);
    }).getContent();

    response.setTotal(data.size());
    response.setData(data);
    response.setCurrentUserId(userId);
    response.setOffset(offset);
    response.setPerPage(itemPerPage);
    return response;
  }



}


