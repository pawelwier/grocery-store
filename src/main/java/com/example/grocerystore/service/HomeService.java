package com.example.grocerystore.service;

import com.example.grocerystore.model.Product;
import com.example.grocerystore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(String name, Double price, Integer quantity, String image, Boolean isPromoted) {
        Product product = new Product(name, price, quantity, image, isPromoted);
        return productRepository.save(product);
    }

    public void updateProduct(Integer id) {
        Product product = productRepository.findById(id).get();
        productRepository.findById(product.getId())
                .ifPresent(p -> {
                    p.setIsPromoted(product.getIsPromoted());
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    p.setImage(product.getImage());
                    p.setQuantity(product.getQuantity());
                    productRepository.save(p);
                });
    }

    public void deleteProduct(Integer id) {
        productRepository.delete(productRepository.findById(id).get());
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).get();
    }
}
