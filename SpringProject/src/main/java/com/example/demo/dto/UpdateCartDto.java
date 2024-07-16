package com.example.demo.dto;

import com.example.demo.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateCartDto {
    private Long cartId;
    private List<Product> products;
}
