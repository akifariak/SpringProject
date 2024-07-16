package com.example.demo.dto;

import com.example.demo.model.Customer;
import lombok.Getter;
import lombok.Setter;

public record CustomerDto (
         String Name,
         String Surname
){
    public static CustomerDto convert(Customer from){
        return new CustomerDto(from.getName(),
        from.getSurname());
    }

}
