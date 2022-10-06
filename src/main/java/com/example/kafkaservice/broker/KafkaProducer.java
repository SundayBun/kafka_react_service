package com.example.kafkaservice.broker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

@Slf4j
@Component
public class KafkaProducer {

    private final ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate;

    @Autowired
    public KafkaProducer(ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate) {
        this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
    }

    public Mono<SenderResult<Void>> producerPublisher(String message, String topicName) {
        log.info("send to topic={}, {}", topicName, message);
        return reactiveKafkaProducerTemplate.send(topicName, message)
                .doOnSuccess(senderResult -> log.info("sent {} offset : {}", message, senderResult.recordMetadata().offset()))
                .doOnError(throwable -> log.error("Error while sending message : {}", throwable.getMessage()));
    }
}
