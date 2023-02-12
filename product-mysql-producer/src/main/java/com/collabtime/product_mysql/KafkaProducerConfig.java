package com.collabtime.product_mysql;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Autowired
    private DefaultKafkaProducerFactory factory;

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        KafkaTemplate temp = new KafkaTemplate<>(factory);
        temp.setMicrometerEnabled(true);
        temp.setObservationEnabled(true);
        return temp;
    }
}