package com.example.grocerystore.service;

import com.example.grocerystore.model.Product;
import com.example.grocerystore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> showAllProducts() {
        return productRepository.findAll();
    }

    public Product deliverProduct(Integer id, Double quantity) {

        Product product = productRepository.findById(id).get();

        product.setQuantity(product.getQuantity() + quantity);

        productRepository.save(product);

        return product;
    }

    public Product changePrice(Integer id, Double price) {

        Product product = productRepository.findById(id).get();

        product.setPrice(price);

        productRepository.save(product);

        return product;
    }


    public Product addNewProduct(String name, Double price, Double quantity, String image) {

        return productRepository.save( new Product(name, price, quantity, image, false));
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
