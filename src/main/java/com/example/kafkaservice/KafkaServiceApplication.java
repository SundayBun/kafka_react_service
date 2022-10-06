package com.example.kafkaservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaServiceApplication.class, args);
    }

}
