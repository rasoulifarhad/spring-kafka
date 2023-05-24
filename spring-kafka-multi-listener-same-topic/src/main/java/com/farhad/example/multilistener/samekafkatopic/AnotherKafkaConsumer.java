package com.farhad.example.multilistener.samekafkatopic;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnotherKafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.topic:books-topic}", groupId = "group_02")
    public void consumePayloadWithHeaders(@Payload Object payload,
                                 @Headers Map<String,Object> headers) {
        log.info("\n-------------------------------\nAnotherKafkaConsumer -> consumePayloadWithHeaders : \n\nPayload: {}, \n\nHeaders: {}\n-------------------------------\n", 
                        payload, 
                        headers);
    }

}
