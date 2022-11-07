package ru.skillbox.diplom.group25.microservice.dialog.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group25.microservice.dialog.dto.DialogMessage;
import ru.skillbox.diplom.group25.microservice.dialog.dto.NotificationInputDto;
import ru.skillbox.diplom.group25.microservice.dialog.dto.NotificationType;

/**
 * KafkaService
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

  private final DialogService dialogService;
  private final KafkaSender kafkaSender;

  @Value(value = "${kafka-topics.dialogs_streaming}")
  private String topicDialogsStreaming;
  @Value(value = "${kafka-topics.notifications}")
  private String topicNotification;


  @Async("taskExecutor")
  @KafkaListener(topics = "${kafka-topics.streaming_dialogs}")
  public void listenStreamingDialogs(ConsumerRecord<String, JsonNode> myRecord){

    log.info("Получено сообщение в топик streaming_dialogs, key {} value {}", myRecord.key(), myRecord.value());
//    {"type":"MESSAGE","accountId":3,"data":{"id":-1,"authorId":4,"messageText":"Все понятно","recipientId":3,"time":1666797173647}}
    dialogService.saveMessage(myRecord.value());

    //Отправляем в стриминг уведомление о получении сообщения и сохранения его в БД
    kafkaSender.sendMessage(topicDialogsStreaming, "Message received and saved to DB", myRecord.value());

    //Отправляем нотификацию о сообщении в microservice-notification
    DialogMessage dialogMessage = dialogService.mapJsonToDialogMessage(myRecord.value());

    if (dialogMessage != null){
      String textMessage = dialogMessage.getData().getMessageText();

      String content = textMessage.length() > 20 ? textMessage.substring(0, 20) + "..." : textMessage;

      NotificationInputDto notification = new NotificationInputDto(dialogMessage.getData().getAuthorId(), dialogMessage.getData().getRecipientId(),
          NotificationType.MESSAGE, content);

      kafkaSender.sendMessage(topicNotification, "New message notification", notification);
    }




  }


}


