package com.collabtime.product_mysql;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TopicProducer topicProducer;

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

    public void products(Model model){
        model.addAttribute("products", productRepository.findAll());
    }

    @GetMapping("/form")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());

        return "addProductForm";
    }

    @Observed(name = "product.add",
            contextualName = "product-add",
            lowCardinalityKeyValues = {"price", "priceValue"})
    @PostMapping("/add")
    public String novo(Product product, BindingResult result) {

        log.info("Adding product with price <{}>", product.getPrice());

        if (result.hasFieldErrors()) {
            return "redirect:/form";
        }

        productRepository.save(product);

        topicProducer.send(product.toString());

        return "redirect:/";
    }

}
