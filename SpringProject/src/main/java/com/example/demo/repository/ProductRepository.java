package com.example.demo.repository;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByProductId(Long id);
}
