package com.farhad.example.multilistener.samekafkatopic;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@KafkaListener(topics = "${spring.kafka.topic:books-topic}", groupId = "group_03",concurrency = "2")
@Slf4j
public class KafkaConsumerHandlerBase {
    
    @KafkaHandler
    public void handleBookEvent(BookEvent bookEvent) {
        log.info("handleBookEvent: bookEvent: {}",
                                bookEvent);
    }

    @KafkaHandler
    public void handleProductCreatedEvent(ProductCreatedEvent productCreatedEvent) {
        log.info("handleProductCreatedEvent: productCreatedEvent: {}",
                                productCreatedEvent);
    }

    @KafkaHandler(isDefault = true)
    public void handleUnknownEvent(Object event) {
        log.info("handleUnknownEvent: event: {}",
                                event);
    }

}
