package ru.skillbox.diplom.group25.microservice.dialog.service;

import java.util.Comparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.diplom.group25.library.core.util.TokenUtil;
import ru.skillbox.diplom.group25.microservice.dialog.dto.DialogDto;
import ru.skillbox.diplom.group25.microservice.dialog.dto.MessageDto;
import ru.skillbox.diplom.group25.microservice.dialog.exception.DialogNotFoundException;
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

  public Page<MessageDto> getAllMessages(Long id, Pageable pageable) {
    log.info("getAllMessages begins, id: {}", id);

//    DialogEntity dialog = dialogRepository.findById(id)
//        .orElseThrow(DialogNotFoundException::new);

    return messageRepository.findMessageEntitiesByDialog_IdOrderByTimeDesc(id, pageable).map(messageMapper::toDto);
  }

  public Page<DialogDto> getAllDialogs(Pageable pageable) {
    log.info("getAllDialogs begins");

    Long userId = TokenUtil.getJwtInfo().getId();

//    DialogEntity dialog = dialogRepository.findById(id)
//        .orElseThrow(DialogNotFoundException::new);

    return dialogRepository.findAllByAuthorIdOrReceiverIdOrderByTimeDesc(userId, userId, pageable).map(dialog -> {
      MessageDto lastMessage = messageMapper.toDto(dialog.getMessages().stream().max(Comparator.comparing(MessageEntity::getId)).orElse(new MessageEntity()));
      return dialogMapper.toDto(dialog, lastMessage);
    });
  }



}


