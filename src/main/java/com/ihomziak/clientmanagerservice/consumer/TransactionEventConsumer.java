package com.ihomziak.clientmanagerservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ihomziak.clientmanagerservice.service.impl.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransactionEventConsumer {

    @Autowired
    private AccountServiceImpl accountService;

    @KafkaListener(topics = {"transfer-transactions-topic"})
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        log.info("Received ConsumerRecord: {}", consumerRecord.value());
        accountService.processTransactionEvent(consumerRecord);
    }
}
