package com.example.grocerystore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Double price;

    private Integer quantity;

    private String image;

    @Column(name = "is_promoted")
    private Boolean isPromoted;


    public Product(String name, Double price, Integer quantity, String image, Boolean isPromoted) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.isPromoted = isPromoted;
    }
}
