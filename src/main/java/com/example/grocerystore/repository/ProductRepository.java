package com.example.grocerystore.repository;

import com.example.grocerystore.model.Product;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select p from Product p where p.name = ?1")
    Product findProductByName(String name);

}
