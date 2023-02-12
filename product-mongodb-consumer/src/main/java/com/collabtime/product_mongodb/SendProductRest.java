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
        log.info("Will send a request to the server");
        String response = restTemplate.getForObject("http://product-rest-server:8088/product/{productId}", String.class, productId);
        log.info("Got response [{}]", response);
    }

}
