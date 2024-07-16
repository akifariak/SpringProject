package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@Entity
@Table(name = "Porder")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @Column(name = "registrationDate" , nullable = false)
    private LocalDate registrationDate;

}
