package com.example.CartAndProducts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private Double quantity;
    private Double price;
    private String expirationDate;
    @ManyToOne
    @JoinColumn(name = "CartId")
    @JsonBackReference
    private Cart cart;
}
