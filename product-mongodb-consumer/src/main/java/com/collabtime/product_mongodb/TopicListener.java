package com.collabtime.product_mongodb;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TopicListener {

    @Value("${topic.name.consumer")
    private String topicName;

    @Autowired
    private ProductRepository productRepository;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){
        System.out.println("Tópico: %s".formatted(topicName));
        System.out.println("key: %s".formatted(payload.key()));
        System.out.println("Headers: %s".formatted(payload.headers()));
        System.out.println("Partion: %s".formatted(payload.partition()));
        System.out.println("Order: %s".formatted(payload.value()));

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Product p = objectMapper.readValue(payload.value(), Product.class);
            productRepository.save(p);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
