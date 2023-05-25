package com.farhad.example.multilistener.samekafkatopic;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Boot {
    
    @Bean
    public ApplicationRunner bootRun(KafkaProducer kafkaProducer) {

        return args -> {

            for (int i = 0; i < 10; i++) {
                BookEvent bookEvent = new BookEvent( String.format("BookEvent Title %d", i+1), 
                                                      String.format("BookEvent Description %d",i+1), 
                                                10.0);
               kafkaProducer.publishBookEventToPartitionedTopic(bookEvent);
            }

            for (int i = 10; i < 20; i++) {
                BookEvent bookEvent = new BookEvent( String.format("BookEvent Title %d", i+1), 
                                                      String.format("BookEvent Description %d",i+1), 
                                                10.0);
               kafkaProducer.publishBookEventAndLog(bookEvent);
            }

            for (int i = 10; i < 20; i++) {
                ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent( String.format("productCreatedEvent Id %d", i+1), 
                                                      String.format("productCreatedEvent Name %d",i+1), 
                                                      50.0,
                                                  10);
               kafkaProducer.publishProductCreatedEventAndLog(productCreatedEvent);
            }
        };
    }
}
