package com.consumer.service;

import com.consumer.model.Tracer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Value("${topic.trace}")
    private String traceTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    public String sendTrace(Tracer trace) throws JsonProcessingException {
        String traceAsMessage = objectMapper.writeValueAsString(trace);
        kafkaTemplate.send(traceTopic, traceAsMessage);
        return "message sent";
    }
}