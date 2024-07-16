package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customer){
        if(customerService.addCustomer(customer)) {
            return ResponseEntity.ok("Customer added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
        }
    }

    @GetMapping("/getCustomers")
    public ResponseEntity<?> getCustomers(){
        List<CustomerDto> customers = customerService.getCustomers();
        if (customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Customer List is Empty");
        } else {
            return ResponseEntity.ok(customers);
        }
    }
}
