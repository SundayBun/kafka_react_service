package com.example.kafkaservice.config;

import org.apache.kafka.common.serialization.Serdes;

import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

@Configuration
public class KafkaStreamConfig {
    final String applicationID = "kafkaService";
    final String bootstrapServers = "localhost:9092,localhost:9093";
    final String inputTopic = "topic_1";
    final String outputTopic = "topic_2";

//
//    public Properties setProperties(){
//        Properties streamsConfiguration = new Properties();
//        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationID);
//        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        return streamsConfiguration;
//    }
//
//    public StreamsBuilder getStreamBuilder(){
//        StreamsBuilder builder = new StreamsBuilder();
//        KStream<String, String> textLines = builder.stream(inputTopic);
//        Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);
//
//        KTable<String, Long> wordCounts = textLines
//                .flatMapValues(value -> Arrays.asList(pattern.split(value.toLowerCase())))
//                .groupBy((key, word) -> word)
//                .count();
//
//        wordCounts.toStream()
//                .foreach((word, count) -> System.out.println("word: " + word + " -> " + count));
//
////        On production, often such streaming job might publish the output to another Kafka topic.
//
//        wordCounts.toStream()
//                .to(outputTopic, Produced.with(Serdes.String(), Serdes.Long()));
//
//    }

}
