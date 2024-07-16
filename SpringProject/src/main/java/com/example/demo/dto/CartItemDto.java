package com.example.demo.dto;

import java.math.BigDecimal;

public record CartItemDto(
        Long productId,
        String productName,
        int quantity,
        BigDecimal totalPrice
) {}
