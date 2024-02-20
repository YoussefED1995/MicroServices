package com.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.producer.model.Product;
import com.producer.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public String createProduct(@RequestBody Product product) throws JsonProcessingException{
        log.info("create product request received");
        return productService.createProdcut(product);
    }

}
