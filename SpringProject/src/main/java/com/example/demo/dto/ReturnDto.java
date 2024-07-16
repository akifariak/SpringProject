package com.example.demo.dto;

import com.example.demo.model.Order;

public record ReturnDto(
        Long id,
        String CustomerName
) {

    public static ReturnDto convert(Order from){
        return new ReturnDto(
                from.getOrderId(),
                from.getCustomer().getName()
        );
    }
}
