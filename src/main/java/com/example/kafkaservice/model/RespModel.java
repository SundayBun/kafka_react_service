package com.example.kafkaservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespModel {

    @JsonProperty("xml")
    private String xmlResp;
    @JsonProperty("id")
    private String messageId;
    @JsonProperty("type")
    private String messType;
    @JsonProperty("timestamp")
    private Date sendingTimestamp;
}
