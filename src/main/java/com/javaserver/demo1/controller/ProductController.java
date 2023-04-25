package com.javaserver.demo1.controller;

import com.javaserver.demo1.model.Product;
import com.javaserver.demo1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // save products
    @PostMapping("products")
    public Product createProduct(@RequestBody Product postProduct) {
        return productService.createProduct(postProduct);
    }
}
