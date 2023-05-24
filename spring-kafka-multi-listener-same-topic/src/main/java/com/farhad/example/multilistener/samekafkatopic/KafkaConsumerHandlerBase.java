package com.farhad.example.multilistener.samekafkatopic;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@KafkaListener(topics = "${spring.kafka.topic:books-topic}", groupId = "group_03")
@Slf4j
public class KafkaConsumerHandlerBase {
    
    @KafkaHandler
    public void handleBookEvent(BookEvent bookEvent) {
        log.info("\n-------------------------------\nKafkaConsumerHandlerBase -> handleBookEvent : \n\nbookEvent: {}\n-------------------------------\n",
                                bookEvent);
    }

    @KafkaHandler
    public void handleProductCreatedEvent(ProductCreatedEvent productCreatedEvent) {
        log.info("\n-------------------------------\nKafkaConsumerHandlerBase -> handleProductCreatedEvent : \n\nproductCreatedEvent: {}\n-------------------------------\n",
                                productCreatedEvent);
    }

    @KafkaHandler(isDefault = true)
    public void handleUnknownEvent(Object event) {
        log.info("\n-------------------------------\nKafkaConsumerHandlerBase -> handleUnknownEvent : \n\nevent: {}\n-------------------------------\n",
                                event);
    }

}
