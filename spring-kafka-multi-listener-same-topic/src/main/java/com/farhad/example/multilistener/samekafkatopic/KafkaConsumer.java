package com.farhad.example.multilistener.samekafkatopic;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {
    

    @KafkaListener(topics = "${spring.kafka.topic:books-topic}", groupId = "group_01") 
    public void consumeMessage(Message<?>  message) {
        log.info("consumeMessage: M: {}, H: {}", 
                        message.getPayload(), 
                        message.getHeaders());
    }

    @KafkaListener(topics = "${spring.kafka.topic:books-topic}", groupId = "group_02")
    public void consumePayloadWithHeaders(@Payload Object payload,
                                 @Headers Map<String,Object> headers) {
        log.info("consumePayloadWithHeaders: Payload: {}, Headers: {}", 
                        payload, 
                        headers);
    }

    @KafkaListener(topics = "${spring.kafka.topic:books-topic}", groupId = "group_05")
    public void consumePayloadWithMetadata(@Payload Object payload,
                                            ConsumerRecordMetadata meta) {
        log.info("consumePayloadWithMetadata: Payload: {}, Headers: {}", 
                        payload, 
                        meta);
    }

    @KafkaListener(topics = "${spring.kafka.topic:books-topic}", groupId = "group_04") 
    public void listenWithHeaderGroup04(Message<?>  message) {
        log.info("consumeMessage : M: {}, H: {}", 
                        message.getPayload(), 
                        message.getHeaders());
    }


    @KafkaListener(topics = "${general.partitioned.topic.name:partitioned-topic}", groupId = "group_partitioned", concurrency = "5") 
    public void listenToPartitionedTopic(Message<?>  message) {
        log.info("consumeMessage : M: {}, H: {}", 
                        message.getPayload(), 
                        message.getHeaders());
    }

}
