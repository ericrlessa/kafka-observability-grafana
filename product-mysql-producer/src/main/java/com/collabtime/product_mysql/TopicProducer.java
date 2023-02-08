package com.collabtime.product_mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopicProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message){
        System.out.println("Payload enviado: %s".formatted(message));
        kafkaTemplate.setObservationEnabled(true);
        kafkaTemplate.send(topicName, message);
    }

}