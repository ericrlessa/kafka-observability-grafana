package com.collabtime.product_kafka_stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@EnableKafka
@EnableKafkaStreams
@SpringBootApplication
public class ProductKafkaStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductKafkaStreamApplication.class, args);
	}

}
