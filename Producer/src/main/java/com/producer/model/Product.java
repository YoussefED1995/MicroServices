package com.producer.model;

import lombok.Data;
import lombok.Value;

@Data
@Value

public class Product {
        Long id;
        String item;
        Double amount;
}
