package com.example.kafkaservice.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;


import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate(KafkaProperties properties) {
        Map<String, Object> producerProperty = properties.buildProducerProperties();
        SenderOptions<String,String> senderOptions=SenderOptions.create(producerProperty);
        senderOptions.maxInFlight(1024);
        return new ReactiveKafkaProducerTemplate<>(senderOptions);
    }
}
