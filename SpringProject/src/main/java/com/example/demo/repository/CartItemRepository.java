package com.example.demo.repository;

import com.example.demo.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findCartItemByCartItemId(Long Id);
//    @Query("SELECT ci FROM CartItem ci JOIN ci.cart c JOIN ci.product p WHERE p.productName LIKE %:letter%")
//    List<CartItem> findCartItemsByProductNameContaining(@Param("letter") String letter);
}
