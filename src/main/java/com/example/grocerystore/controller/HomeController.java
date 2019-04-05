package com.example.grocerystore.controller;

import com.example.grocerystore.model.Product;
import com.example.grocerystore.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return homeService.getAllProducts();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Double quantity,
                              @RequestParam Boolean isPromoted) {
        return homeService.addProduct(name, price, quantity, isPromoted);
    }

    @PutMapping("/products")
    public void updateProduct(@RequestParam (required = false) Integer id) {
        homeService.updateProduct(id);
    }

    @DeleteMapping("/products")
    public void deleteProduct(@RequestParam (required = false) Integer id) {
        homeService.deleteProduct(id);
    }

}
