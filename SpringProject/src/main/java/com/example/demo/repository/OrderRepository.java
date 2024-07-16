package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long>{

    Order findOrderByCustomer(Customer customer);

    @Query("SELECT o FROM Order o WHERE o.customer.name = :customerName")
    List<Order> findOrderByCustomerName(@Param("customerName") String customerName);
}
