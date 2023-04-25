package com.javaserver.demo1.service;

import com.javaserver.demo1.model.Product;
import com.javaserver.demo1.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(@RequestBody Product postProduct) {
        return productRepository.save(postProduct);
    }
}
