package com.example.kafkaservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static <J> J getObject(String json, Class<J> jClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return (J) objectMapper.readValue(json, jClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getString(Object model) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
