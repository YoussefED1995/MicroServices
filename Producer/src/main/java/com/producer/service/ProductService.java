package com.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.producer.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ProductService {
    private final Producer producer;

    @Autowired
    public ProductService(Producer producer) {
        this.producer = producer;
    }

    public String createProdcut(Product product) throws JsonProcessingException {
        return producer.sendMessage(product);
    }
}
