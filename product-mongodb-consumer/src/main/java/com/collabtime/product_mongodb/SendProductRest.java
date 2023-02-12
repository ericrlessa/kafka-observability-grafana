package com.collabtime.product_mongodb;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class SendProductRest {

    private static final Logger log = LoggerFactory.getLogger(SendProductRest.class);

    @Autowired
    private ObservationRegistry registry;

    @Autowired
    private RestTemplate restTemplate;

    public void send(String productId, BigDecimal price) {
        // Example of using the Observability API manually
        // <my.observation> is a "technical" name that does not depend on the context. It will be used to name e.g. Metrics
        Observation.createNotStarted("price.observation", registry)
                // High cardinality means that the number of potential values can be large. High cardinality entries will end up in e.g. Spans
                .highCardinalityKeyValue("productId", productId)
                .highCardinalityKeyValue("price", price.toString())
                // <command-line-runner> is a "contextual" name that gives more details within the provided context. It will be used to name e.g. Spans
                .contextualName("product-price")
                // The following lambda will be executed with an observation scope (e.g. all the MDC entries will be populated with tracing information). Also the observation will be started, stopped and if an error occurred it will be recorded on the observation
                .observe(() -> {
                    log.info("Will send a request to the server"); // Since we're in an observation scope - this log line will contain tracing MDC entries ...
                    String response = restTemplate.getForObject("http://product-rest-server:8088/product/{productId}", String.class, productId); // Boot's RestTemplate instrumentation creates a child span here
                    log.info("Got response [{}]", response); // ... so will this line
                });
    }

}
