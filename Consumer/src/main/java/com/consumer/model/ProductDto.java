package com.consumer.model;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class ProductDto {
    String item;
    Double amount;

}
