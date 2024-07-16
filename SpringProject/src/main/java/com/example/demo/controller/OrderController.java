package com.example.demo.controller;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order/")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody Customer customer){
        if(orderService.placeOrder(customer)) {
            return ResponseEntity.ok("PlaceOrder successful");
        } else {
            return ResponseEntity.ok("Error");
        }
    }
    @GetMapping("/getOrderForCode")
    public ResponseEntity<OrderDto> getOrderForCode(@RequestBody Customer customer){
        try {
            OrderDto orderDto = orderService.getOrderForCode(customer);
            return ResponseEntity.ok(orderDto);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
