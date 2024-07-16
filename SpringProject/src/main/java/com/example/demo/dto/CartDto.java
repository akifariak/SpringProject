package com.example.demo.dto;

import com.example.demo.model.CartItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record CartDto(
        Long productId,
        String productName,
        BigDecimal productPrice,
        int quantity
) {
    public static List<CartDto> convert(List<CartItem> from) {
        return from.stream()
                .map(cartItem -> new CartDto(
                        cartItem.getProduct().getProductId(),
                        cartItem.getProduct().getProductName(),
                        cartItem.getProduct().getPrice(),
                        cartItem.getQuantity()
                ))
                .collect(Collectors.toList());
    }
}
