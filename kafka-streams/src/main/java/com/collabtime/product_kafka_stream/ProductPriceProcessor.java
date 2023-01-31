package com.collabtime.product_kafka_stream;

import java.math.BigDecimal;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceProcessor {

    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Value("${topic.name.origin}")
    private String topicNameOrigin;

    @Value("${topic.name.target}")
    private String topicNameTarget;

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> messageStream = streamsBuilder
            .stream(topicNameOrigin, Consumed.with(STRING_SERDE, STRING_SERDE));

        KStream<String, String> valuesGreaterThan100 = messageStream
            .mapValues(Product::valueOf)
            .filter((k, v) -> v.getPrice().doubleValue() > 100.0)
            .peek((k, v) -> v.setPrice(v.getPrice().multiply(BigDecimal.TEN)))
            .mapValues(v -> v.toString());

        valuesGreaterThan100.to(topicNameTarget);
    }

}