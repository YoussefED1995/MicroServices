package com.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.model.Product;
import com.producer.model.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Value("${topic.name}")
    private String topic;

    @Value("${topic.trace}")
    private String traceTopic;


    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public String sendMessage(Product product) throws JsonProcessingException {
        String productAsMessage = objectMapper.writeValueAsString(product);
        kafkaTemplate.send(topic, productAsMessage);
        return "message sent";
    }

    public String sendTraceToKafkaTopic(Trace trace) throws JsonProcessingException {
        String traceAsMessage = objectMapper.writeValueAsString(trace);
        System.out.println(traceAsMessage);
        kafkaTemplate.send(traceTopic, traceAsMessage);
        return "message sent";
    }

}