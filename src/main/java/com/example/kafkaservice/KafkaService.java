package com.example.kafkaservice;

import com.example.kafkaservice.broker.KafkaConsumer;
import com.example.kafkaservice.broker.KafkaProducer;
import com.example.kafkaservice.model.RespModel;
import com.example.kafkaservice.repository.MyEntity;
import com.example.kafkaservice.repository.MyRepository;
import com.example.kafkaservice.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@Slf4j
@Component
public class KafkaService {

    private final KafkaProducer producer;
    private final KafkaConsumer consumer;
    private final MyRepository repository;
    private final String producerTopic;

    @Autowired
    public KafkaService(KafkaProducer producer, KafkaConsumer consumer, MyRepository repository,
                        @Value(value = "${topicNameProducer}") String producerTopic) {
        this.producer = producer;
        this.consumer = consumer;
        this.repository = repository;
        this.producerTopic = producerTopic;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void execute() {
        consumer.consumerPublisher()
                .flatMap(message -> Mono.just(JsonUtil.getObject(message, RespModel.class)))
                .flatMap(model -> repository.findByMessageId(model.getMessageId())
                        .switchIfEmpty(Mono.just(new MyEntity()))
                        .doOnError(e -> log.error("Error due to getEntity: ", e))
                        .onErrorResume(e -> Mono.empty())
                        .map(entity -> Tuples.of(model, entity))
                )
                .flatMap(tuple -> {
                    if (tuple.getT2().getId() == null) {
                        return Mono.just(tuple);
                    }
                    return repository.updateByMessageId(tuple.getT1().getSendingTimestamp(), tuple.getT1().getMessageId())
                            .onErrorResume(e -> Mono.empty())
                            .map(i -> tuple);
                })
                .flatMap(tuple -> {
                    if (tuple.getT2().getLost()) {
                        log.info("Message is lost. OriginMessageId=" + tuple.getT1().getMessageId());
                        return Mono.empty();
                    }
                    return Mono.just(tuple);
                })
                .flatMap(tuple -> producer.producerPublisher(JsonUtil.getString(tuple.getT1()), producerTopic))
                .subscribe();
    }
}
