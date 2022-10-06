package com.example.kafkaservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class KafkaServiceTest {

    @Autowired
    private KafkaService kafkaService;

    @BeforeEach
    void setUp() {
    }

//    @Test
//    void sendMessage() {
//        kafkaService.sendMessage();
//    }
}