package com.consumer.service;


import com.consumer.model.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    private static final String topic = "${topic.name}";

    private final ObjectMapper objectMapper;
    private final ProductService productService;


    @Autowired
    public Consumer(ObjectMapper objectMapper, ProductService productService) {
        this.objectMapper = objectMapper;
        this.productService = productService;
    }

    @KafkaListener(topics = topic)
    public void consumeMessage(String message) throws JsonProcessingException {
        log.info("message consumed {}", message);
        ProductDto productDto = objectMapper.readValue(message, ProductDto.class);
        productService.persistOrder(productDto);
    }
}
