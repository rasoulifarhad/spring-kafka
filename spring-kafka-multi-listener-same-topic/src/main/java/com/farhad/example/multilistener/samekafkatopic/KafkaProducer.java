package com.farhad.example.multilistener.samekafkatopic;

import java.util.UUID;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    
    private final KafkaTemplate<String,BookEvent> kafkaTemplate;
    private final KafkaTemplate<String,ProductCreatedEvent> productCreatedEventKafkaTemplate;
    private final NewTopic topic;    
    
    public void publishBookEvent(BookEvent bookEvent) {
        kafkaTemplate.send(topic.name(), UUID.randomUUID().toString(), bookEvent);
    }

    public void publishBookEventAndLog(BookEvent bookEvent){
        ListenableFuture<SendResult<String, BookEvent>> future = kafkaTemplate.send(topic.name(), UUID.randomUUID().toString(), bookEvent);
        future.addCallback(result -> log.info("Success: {}", result.getRecordMetadata())
                        , ex -> log.error("Error: {}", ex.getMessage()));
    }

    public void publishProductCreatedEvent(ProductCreatedEvent productCreatedEvent) {
        productCreatedEventKafkaTemplate.send(topic.name(), UUID.randomUUID().toString(), productCreatedEvent);
    }

    public void publishProductCreatedEventAndLog(ProductCreatedEvent productCreatedEvent){
        ListenableFuture<SendResult<String, ProductCreatedEvent>> future = productCreatedEventKafkaTemplate.send(topic.name(), UUID.randomUUID().toString(), productCreatedEvent);
        future.addCallback(result -> log.info("Success: {}", result.getRecordMetadata())
                        , ex -> log.error("Error: {}", ex.getMessage()));
    }

    
}
