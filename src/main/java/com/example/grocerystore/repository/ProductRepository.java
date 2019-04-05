package com.example.grocerystore.repository;

import com.example.grocerystore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
