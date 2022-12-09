package com.example.kafkaservice;

import com.example.kafkaservice.broker.KafkaProducer;
import com.example.kafkaservice.model.RespModel;
import com.example.kafkaservice.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class KafkaProducerTest {

    @Autowired
    private KafkaProducer kafkaProducer;
    @Value("${topicNameConsumer}")
    private String consumerTopic;

    @BeforeEach
    void setUp() {
    }

    @Test
    void sendMessage() throws InterruptedException {
        String message=createMessage();
        kafkaProducer.producerPublisher(message,consumerTopic).subscribe();
        Thread.sleep(15000);
    }

    private String createMessage() {
        RespModel respModel=new RespModel();
        respModel.setMessageId("1111");
        respModel.setMessType("Type");
        respModel.setXmlResp("XML");
        return JsonUtil.getString(respModel);
    }
}