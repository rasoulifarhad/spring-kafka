package com.farhad.example.multilistener.samekafkatopic;

import java.util.UUID;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducer {
    
    private final KafkaTemplate<String,BookEvent> kafkaTemplate;
    private final KafkaTemplate<String,ProductCreatedEvent> productCreatedEventKafkaTemplate;
    private final NewTopic bookTopic;    
    private final NewTopic bookPartitionedTopic;    

    
    public KafkaProducer(KafkaTemplate<String, BookEvent> kafkaTemplate,
            KafkaTemplate<String, ProductCreatedEvent> productCreatedEventKafkaTemplate, 
            @Qualifier("bookTopic") NewTopic bookTopic,
            @Qualifier("bookPartitionedTopic") NewTopic bookPartitionedTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.productCreatedEventKafkaTemplate = productCreatedEventKafkaTemplate;
        this.bookTopic = bookTopic;
        this.bookPartitionedTopic = bookPartitionedTopic;
    }

    public void publishBookEvent(BookEvent bookEvent) {
        kafkaTemplate.send(bookTopic.name(), UUID.randomUUID().toString(), bookEvent);
    }

    public void publishBookEventToPartitionedTopic(BookEvent bookEvent) {
        kafkaTemplate.send(bookPartitionedTopic.name(), UUID.randomUUID().toString(), bookEvent);
    }

    public void publishBookEventAndLog(BookEvent bookEvent){
        ListenableFuture<SendResult<String, BookEvent>> future = kafkaTemplate.send(bookTopic.name(), UUID.randomUUID().toString(), bookEvent);
        future.addCallback(result -> log.info("Success: {}", result.getRecordMetadata())
                        , ex -> log.error("Error: {}", ex.getMessage()));
    }

    public void publishBookEventToPartitionedTopicAndLog(BookEvent bookEvent){
        ListenableFuture<SendResult<String, BookEvent>> future = kafkaTemplate.send(bookPartitionedTopic.name(), UUID.randomUUID().toString(), bookEvent);
        future.addCallback(result -> log.info("Success: {}", result.getRecordMetadata())
                        , ex -> log.error("Error: {}", ex.getMessage()));
    }

    public void publishProductCreatedEvent(ProductCreatedEvent productCreatedEvent) {
        productCreatedEventKafkaTemplate.send(bookTopic.name(), UUID.randomUUID().toString(), productCreatedEvent);
    }

    public void publishProductCreatedEventAndLog(ProductCreatedEvent productCreatedEvent){
        ListenableFuture<SendResult<String, ProductCreatedEvent>> future = productCreatedEventKafkaTemplate.send(bookTopic.name(), UUID.randomUUID().toString(), productCreatedEvent);
        future.addCallback(result -> log.info("Success: {}", result.getRecordMetadata())
                        , ex -> log.error("Error: {}", ex.getMessage()));
    }

    
    public void publishBookEventAndLog(ProductCreatedEvent productCreatedEvent){
        ListenableFuture<SendResult<String, ProductCreatedEvent>> future = productCreatedEventKafkaTemplate.send(bookTopic.name(), UUID.randomUUID().toString(), productCreatedEvent);
        future.addCallback(result -> log.info("Success: {}", result.getRecordMetadata())
                        , ex -> log.error("Error: {}", ex.getMessage()));
    }
}
