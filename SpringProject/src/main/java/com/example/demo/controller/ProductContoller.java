package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductUpdateDto;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductContoller {

private final ProductService productService;

    public ProductContoller(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto product){
        if(productService.addProduct(product)) {
            return ResponseEntity.ok("Product added successfully");
        } else {
            return ResponseEntity.ok("Error");
        }
    }

    @PatchMapping("/{productId}/updateProduct")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId,@RequestBody ProductUpdateDto product){
        if(productService.updateProduct(productId,product)){
            return ResponseEntity.status(HttpStatus.OK).body("Updated Product");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error UpdateProduct");
    }

    @PostMapping("/{id}/deleteProduct")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        boolean delete = productService.deleteProduct(id);
        if (delete==true) {

            return ResponseEntity.status(HttpStatus.OK).body("Deleted Product");
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleted Product");
        }
    }

    @GetMapping("/getProducts")
    public ResponseEntity<?> getProducts(){
        List<ProductDto> products = productService.getProducts();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no product");
        } else {
            return ResponseEntity.ok(products);
        }
    }

}
