package com.collabtime.product_mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public String home(Model model) {
        products(model);
        return "product";
    }
   
    @GetMapping("/")
    public String setor(Model model) {
        products(model);
        return "product";
    }

    public List<Product> products(Model model){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        model.addAttribute("products", products);
        return products;
    }

}