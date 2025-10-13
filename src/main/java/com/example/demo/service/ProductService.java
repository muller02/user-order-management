package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProductById(Long id);
    public Product createProduct(Product product);
}