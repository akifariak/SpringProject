package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final CartService cartService;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, CustomerRepository customerRepository, CartService cartService, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    public boolean placeOrder(Customer customer) {
        try {
            Cart c = cartRepository.findCartByCustomer(customer);
            if (c == null) {
                throw new Exception("Cart not found for customer: " + customer.getCustomerId());
            }
            Order o = Order.builder()
                    .cart(c)
                    .customer(customer)
                    .registrationDate(LocalDate.now())
                    .build();

            Customer findCustomer = customerRepository.findById(customer.getCustomerId())
                    .orElseThrow(() -> new Exception("Customer not found: " + customer.getCustomerId()));
            o.setCustomer(findCustomer);

            orderRepository.save(o);
            cartService.emptyCart(customer);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


    public OrderDto getOrderForCode(Customer customer) {
        try {
            Order order = orderRepository.findOrderByCustomer(customer);
            if (order == null) {
                throw new Exception("Order not found for customer");
            }
            return OrderDto.convert(order);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }




    public Order getAllOrdersForCustomer(Long customerId) {
        Customer c = customerRepository.findCustomerByCustomerId(customerId);
        return null;
    }


}
