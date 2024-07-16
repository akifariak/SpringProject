package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductUpdateDto {
    private Long product_id;
    private String productName;
    private Integer amount;
    private BigDecimal price;
}
