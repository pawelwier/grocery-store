package com.example.grocerystore.controller;

import com.example.grocerystore.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @GetMapping("/")
    public String showAllProducts(ModelMap modelMap) {
        modelMap.put("products", mainService.showAllProducts());
        return "main";
    }

    @GetMapping("/product/{id}")
    public String makeDeliveryForProduct(@PathVariable Integer id,
                                         @RequestParam Double delivery_quantity) {

        mainService.deliverProduct(id, delivery_quantity);

        return "redirect:/";
    }

    @GetMapping("/price/{id}")
    public String changePriceForProduct(@PathVariable Integer id,
                                         @RequestParam Double new_price) {

        mainService.changePrice(id, new_price);

        return "redirect:/";
    }
}
