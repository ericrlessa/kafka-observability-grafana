package com.collabtime.product_mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private ConsumerFactory consumerFactory;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
      kafkaListenerContainerFactory() {
   
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
          new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setMicrometerEnabled(true);
        factory.getContainerProperties().setObservationEnabled(true);
        return factory;
    }
}