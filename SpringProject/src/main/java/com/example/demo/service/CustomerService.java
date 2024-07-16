package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    public final CustomerRepository customerRepository;
    private final CartService cartService;

    public CustomerService(CustomerRepository customerRepository, CartService cartService) {
        this.customerRepository = customerRepository;
        this.cartService = cartService;
    }

    public boolean addCustomer(CustomerDto customer){
        try {
            Customer c = Customer.builder().name(customer.Name()).surname(customer.Surname()).build();
            customerRepository.save(c);
            cartService.createCart(c);
            return true;
        }catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public List<CustomerDto> getCustomers(){
        try {
            List<Customer> customers = customerRepository.findAll();
            return customers.stream()
                    .map(CustomerDto::convert)
                    .collect(Collectors.toList());
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
