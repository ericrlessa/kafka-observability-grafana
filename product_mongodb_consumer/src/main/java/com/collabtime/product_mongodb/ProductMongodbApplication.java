package com.collabtime.product_mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableMongoRepositories
@EnableKafka
public class ProductMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMongodbApplication.class, args);
	}

}
