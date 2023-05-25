package com.farhad.example.multilistener.samekafkatopic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    @Bean("bookTopic")
    public NewTopic bookTopic(@Value("${spring.kafka.topic:books-topic}") String topic) {
        return TopicBuilder
                        .name(topic)
                        .partitions(1)
                        .replicas(1)
                        .build();
    }

    public NewTopic generalNoPartitionedTopic(@Value("${general.nopartitioned.topic.name:not-partitioned-topic}") String topic) {
        return TopicBuilder
                        .name(topic)
                        .partitions(1)
                        .replicas(1)
                        .build();
    }

    @Bean("bookPartitionedTopic")
    public NewTopic generalPartitionedTopic(@Value("${general.partitioned.topic.name:partitioned-topic}") String topic) {
        return TopicBuilder
                        .name(topic)
                        .partitions(5)
                        .replicas(1)
                        .build();
    }
}
