package ru.skillbox.diplom.group25.microservice.dialog.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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


  @Async("taskExecutor")
  @KafkaListener(topics = "${kafka-topics.streaming_dialogs}")
  public void listenStreamingDialogs(ConsumerRecord<String, JsonNode> myRecord){

    log.info("Получено сообщение в топик streaming_dialogs, key {} value {}", myRecord.key(), myRecord.value());
//    {"type":"MESSAGE","accountId":3,"data":{"id":-1,"authorId":4,"messageText":"Все понятно","recipientId":3,"time":1666797173647}}
    dialogService.saveMessage(myRecord.value());

  }


}


