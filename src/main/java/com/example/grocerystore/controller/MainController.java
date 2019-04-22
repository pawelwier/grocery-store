package com.example.grocerystore.controller;

import com.example.grocerystore.model.Product;
import com.example.grocerystore.repository.ProductRepository;
import com.example.grocerystore.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @Autowired
    ProductRepository productRepository;

    static List<Product> list;

    @GetMapping("/")
    public String showAllProducts(ModelMap modelMap) {
        modelMap.put("products", mainService.showAllProducts());
        return "main";
    }

    @GetMapping("/product/{id}")
    public String makeDeliveryForProduct(@PathVariable Integer id,
                                         @RequestParam(required = false) Double delivery_quantity,
                                         ModelMap modelMap) {

        if (delivery_quantity == null) {
            modelMap.put("products", mainService.showAllProducts());
            modelMap.put("item_message", "Wpisz ilość.");
            return "main";
        }
        else {
            mainService.deliverProduct(id, delivery_quantity);
            return "redirect:/";
        }
    }

    @GetMapping("/price/{id}")
    public String changePriceForProduct(@PathVariable Integer id,
                                        @RequestParam(required = false) Double new_price,
                                        ModelMap modelMap) {

        if (new_price == null) {
            modelMap.put("products", mainService.showAllProducts());
            modelMap.put("item_message", "Wpisz cenę.");
            return "main";
        }
        else {
            mainService.changePrice(id, new_price);
            return "redirect:/";
        }
    }

    @GetMapping("/new")
    public String showAddProductForm() {
        return "newproduct";
    }

    @GetMapping("/newproduct")
    public String showAddProductForm(@RequestParam(required = false) String new_name,
                                     @RequestParam(required = false) Double new_price,
                                     @RequestParam(required = false) Double new_quantity,
                                     @RequestParam(required = false) String new_image,
                                     ModelMap modelMap) {
        if (new_name.equals("") || new_image.equals("") || new_price == null || new_quantity == null) {
            modelMap.put("new_message", "Wpisz dane produktu");
        }
        else {
            mainService.addNewProduct(new_name, new_price, new_quantity, new_image);
            modelMap.put("new_message", "Dodano produkt");
        }
        return "newproduct";
    }

    @GetMapping("/delete")
    public String showDeleteForm(ModelMap modelMap) {

        modelMap.put("products", mainService.showAllProducts());

        return "deleteform";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Integer id,
                                    ModelMap modelMap) {

        mainService.deleteProduct(id);
        modelMap.put("delete_message", "Usunięto produkt");
        modelMap.put("products", mainService.showAllProducts());

        return "deleteform";
    }

    @GetMapping("/buy")
    public String buyProductSelect(ModelMap modelMap) {

        modelMap.put("products", mainService.showAllProducts());

        return "buyproducts";
    }

    @GetMapping("/buyproduct")
    public String buyProduct(@RequestParam (required = false) String name,
                             @RequestParam (required = false) Double quantity,
                             ModelMap modelMap) {

        Product product = productRepository.findProductByName(name);

        if (quantity == null || quantity == 0) {
            return "redirect:/buyempty";

        } else if (product.getQuantity() >= quantity)
            {
            mainService.sellProductQuantity(name, quantity);
            return "redirect:/";
        }
         else {
             return "redirect:/buyerror";
        }

    }

    @GetMapping ("/buyerror")
    public String displayQuantityError(ModelMap modelMap) {

        modelMap.put("quantity_error", "Brak podanej ilości produktu w magazynie.");

        modelMap.put("products", mainService.showAllProducts());

        return "buyproducts";
    }

    @GetMapping ("/buyempty")
    public String displayQuantityEmpty(ModelMap modelMap) {

        modelMap.put("quantity_error", "Wybierz ilość.");

        modelMap.put("products", mainService.showAllProducts());

        return "buyproducts";
    }

}
