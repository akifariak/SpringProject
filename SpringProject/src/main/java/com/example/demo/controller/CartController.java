package com.example.demo.controller;

import com.example.demo.dto.AddChartDto;
import com.example.demo.dto.CartDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UpdateCartDto;
import com.example.demo.model.Customer;
import com.example.demo.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cart/")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{customerId}/addCart")
    public ResponseEntity<String> addCart(@PathVariable Long customerId, @RequestBody AddChartDto addChartDto){
        if(cartService.addCart(customerId,addChartDto)) {
            return ResponseEntity.ok("Product added successfully to Cart");
        } else {
            return ResponseEntity.ok("Error");
        }
    }

//    @PatchMapping("/updateCart")
//    public ResponseEntity<String> updateCart(@RequestBody UpdateCartDto updateCartDto){
//        if(cartService.updateCart(updateCartDto)){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Updated Cart");
//        }
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error updateCart");
//    }
    @GetMapping("/getCart")
    public ResponseEntity<List<CartDto>> getCart(@RequestBody Customer customer){
        return ResponseEntity.ok(cartService.getCart(customer));
    }

    @GetMapping("/getCartTotalPrice")
    public ResponseEntity<List<BigDecimal>> getCartByLetter(@RequestBody String Letter){
        return ResponseEntity.ok(cartService.getCartTotalPrice(Letter));
    }

    @PatchMapping("/emptyCart")
    public ResponseEntity<String> updateCart(@RequestBody Customer customer){
        if(cartService.emptyCart(customer)){
            return ResponseEntity.status(HttpStatus.OK).body("Cart cleared");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error ");
    }

    @PostMapping("/{customerId}/removeProductFromCart")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long customerId, @RequestBody AddChartDto  addChartDto){
        if(cartService.removeProductsFromCart(customerId,addChartDto)) {
            return ResponseEntity.ok("Product removed successfully to Cart");
        } else {
            return ResponseEntity.ok("Error");
        }
    }
}
