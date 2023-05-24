package com.farhad.example.multilistener.samekafkatopic;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Boot {
    
    @Bean
    public ApplicationRunner bootRun(KafkaProducer kafkaProducer) {

        return args -> {

            BookEvent bookEvent = new BookEvent("BookEvent Title 1", "BookEvent Description 1", 10.0);
            ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent("productCreatedEvent Id 1", "productCreatedEvent Name 1", 50.0, 10);

            kafkaProducer.publishBookEventAndLog(bookEvent);
            kafkaProducer.publishProductCreatedEventAndLog(productCreatedEvent);
        };
    }
}
