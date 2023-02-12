package com.collabtime.product_rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

@RestController
class MyController {

	private static final Logger log = LoggerFactory.getLogger(MyController.class);
	private final MyProductService myProductService;

	MyController(MyProductService myProductService) {
		this.myProductService = myProductService;
	}

	@GetMapping("/product/{productId}")
	String userName(@PathVariable("productId") String productId) {
		log.info("Got a request");
		return myProductService.productData(productId);
	}
}


@Service
class MyProductService {

	private static final Logger log = LoggerFactory.getLogger(MyProductService.class);

	private final Random random = new Random();

	String productData(String productId) {
		log.info("Getting user name for user with id <{}>", productId);
		try {
			Thread.sleep(random.nextLong(200L)); // simulates latency
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return "foo";
	}
}

