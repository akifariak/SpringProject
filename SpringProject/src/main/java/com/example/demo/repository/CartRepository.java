package com.example.demo.repository;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByCustomer(Customer customer);
    Cart findCartByCartId(Long id);

    @Query("SELECT DISTINCT c FROM Cart c JOIN c.cartItems ci JOIN ci.product p WHERE p.productName LIKE %:letter%")
    List<Cart> findCartsByProductNameContaining(@Param("letter") String letter);



}
