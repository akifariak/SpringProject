package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductUpdateDto;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean addProduct(ProductDto product) {
        try {
            Product p = Product.builder()
                    .productName(product.productName())
                    .amount(product.amount())
                    .price(product.price())
                    .build();
            productRepository.save(p);
            return true;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


    public boolean updateProduct(Long productId,ProductUpdateDto productUpdateDto){
        try {
            Product p = productRepository.findProductByProductId(productId);
            p.setProductName(productUpdateDto.getProductName());
            p.setAmount(productUpdateDto.getAmount());
            p.setPrice(productUpdateDto.getPrice());
            productRepository.save(p);
            return true;

        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(Long id){
        try {
            Product p = productRepository.findProductByProductId(id);
            productRepository.deleteById(p.getProductId());
            return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public List<ProductDto> getProducts() {
        try {
            List<Product> products = productRepository.findAll();

            return products.stream()
                    .map(ProductDto::convert)
                    .collect(Collectors.toList());
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Product getProduct(Long productId){
        try {
            return productRepository.findProductByProductId(productId);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

}
