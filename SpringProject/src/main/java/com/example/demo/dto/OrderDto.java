package com.example.demo.dto;

import com.example.demo.model.Order;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public record OrderDto (
        List<CartItemDto> cartItems

) {
    public static OrderDto convert(Order order) {
        List<CartItemDto> cartItems = order.getCart().getCartItems().stream()
                .map(cartItem -> new CartItemDto(
                        cartItem.getProduct().getProductId(),
                        cartItem.getProduct().getProductName(),
                        cartItem.getQuantity(),
                        cartItem.getTotalPrice()))
                .collect(Collectors.toList());

        return new OrderDto(
                cartItems
                );
    }
}
