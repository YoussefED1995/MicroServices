package com.consumer.controller;

import com.consumer.model.Product;
import com.consumer.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping
    public List<Product> getAll() {
       return productRepo.findAll();
    }

    @PostMapping("/exist")
    public String get(@RequestBody Product product) {
        if(productRepo.findById(product.getId()).isPresent())
            return "true";
        else
            return "false";

    }
}
