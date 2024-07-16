package com.example.demo.dto;

import com.example.demo.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public record ProductDto (
        String productName,
        Integer amount,
        BigDecimal price

){

public static ProductDto convert(Product from){
    return new ProductDto(from.getProductName(),
    from.getAmount(),
    from.getPrice());
}
}
