package com.farhad.example.multilistener.samekafkatopic;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {
    

    @KafkaListener(topics = "${spring.kafka.topic:books-topic}", groupId = "group_01") 
    public void consumeMessage(Message<?>  message) {
        log.info("\n-------------------------------\nKafkaConsumer -> consumeMessage : \n\nM: {}, \n\nH: {}\n-------------------------------\n", 
                        message.getPayload(), 
                        message.getHeaders());
    }

    @KafkaListener(topics = "${spring.kafka.topic:books-topic}", groupId = "group_04") 
    public void listenWithHeaderGroup04(Message<?>  message) {
        log.info("\n-------------------------------\nKafkaConsumer -> consumeMessage : \n\nM: {}, \n\nH: {}\n-------------------------------\n", 
                        message.getPayload(), 
                        message.getHeaders());
    }

}
