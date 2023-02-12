package com.collabtime.product_rest;

import java.util.Random;
import java.util.stream.StreamSupport;

import io.micrometer.common.KeyValue;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import io.micrometer.observation.aop.ObservedAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	// tag::aspect[]
	// To have the @Observed support we need to register this aspect
	@Bean
	ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
		return new ObservedAspect(observationRegistry);
	}
	// end::aspect[]

}

// tag::controller[]
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
// end::controller[]

// tag::service[]
@Service
class MyProductService {

	private static final Logger log = LoggerFactory.getLogger(MyProductService.class);

	private final Random random = new Random();

	// Example of using an annotation to observe methods
	// <user.name> will be used as a metric name
	// <getting-user-name> will be used as a span  name
	// <userType=userType2> will be set as a tag for both metric & span
	@Observed(name = "price.observation",
			contextualName = "product-price",
			lowCardinalityKeyValues = {"price", "priceValue"})
	String productData(String userId) {
		log.info("Getting user name for user with id <{}>", userId);
		try {
			Thread.sleep(random.nextLong(200L)); // simulates latency
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return "foo";
	}
}
// end::service[]

// tag::handler[]
// Example of plugging in a custom handler that in this case will print a statement before and after all observations take place
@Component
class MyHandler implements ObservationHandler<Observation.Context> {

	private static final Logger log = LoggerFactory.getLogger(MyHandler.class);

	@Override
	public void onStart(Observation.Context context) {
		log.info("Before running the observation for context [{}], userType [{}]", context.getName(), getUserTypeFromContext(context));
	}

	@Override
	public void onStop(Observation.Context context) {
		log.info("After running the observation for context [{}], userType [{}]", context.getName(), getUserTypeFromContext(context));
	}

	@Override
	public boolean supportsContext(Observation.Context context) {
		return true;
	}

	private String getUserTypeFromContext(Observation.Context context) {
		return StreamSupport.stream(context.getLowCardinalityKeyValues().spliterator(), false)
				.filter(keyValue -> "price".equals(keyValue.getKey()))
				.map(KeyValue::getValue)
				.findFirst()
				.orElse("UNKNOWN");
	}
}
// end::handler[]
