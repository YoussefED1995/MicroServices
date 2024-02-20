package com.consumer.service;

import com.consumer.model.Product;
import com.consumer.model.ProductDto;
import com.consumer.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepo productRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
    }
    public void persistOrder(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product persisted = productRepo.save(product);
        log.info("food order persisted {}", persisted);
    }
}
