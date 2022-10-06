package com.example.kafkaservice.broker;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Component
public class KafkaConsumer {

    private final ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate;

    @Autowired
    public KafkaConsumer(ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate) {
        this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
    }

    public Flux<String> consumerPublisher() {
        return reactiveKafkaConsumerTemplate
                .receiveAutoAck()
                // .delayElements(Duration.ofSeconds(2L)) // BACKPRESSURE
                .parallel()
                .runOn(Schedulers.parallel())
                .doOnNext(consumerRecord -> log.info("Received key={}, value={} from topic={}, offset={}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset())
                )
                .map(ConsumerRecord::value)
                .doOnNext(fakeConsumerDTO -> log.info("Successfully consumed {}={}", String.class.getSimpleName(), fakeConsumerDTO))
                .doOnError(throwable -> log.error("Error while consuming : {}", throwable.getMessage()))
                .sequential();
    }
}
