package com.collabtime.product_kafka_stream;

import static org.apache.kafka.streams.StreamsConfig.APPLICATION_ID_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${topic.name.origin}")
    private String topicNameOrigin;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    KafkaStreamsConfiguration kStreamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(APPLICATION_ID_CONFIG, "streams-app");
        props.put(BOOTSTRAP_SERVERS_CONFIG, "kafka:9094");
        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        return new KafkaStreamsConfiguration(props);
    }

    @Bean(KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public StreamsBuilderFactoryBean streamsBuilderFactoryBean() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 0);
        props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, 1);

        Properties properties = new Properties();
        props.forEach(properties::put);
        StreamsBuilderFactoryBean streamsBuilderFactoryBean = new StreamsBuilderFactoryBean();
        streamsBuilderFactoryBean.setStreamsConfiguration(properties);
        return streamsBuilderFactoryBean;
    }

    @Bean
    NewTopic inputTopic() {
        return TopicBuilder.name(topicNameOrigin)
            .partitions(1)
            .replicas(1)
            .build();
    }

}
