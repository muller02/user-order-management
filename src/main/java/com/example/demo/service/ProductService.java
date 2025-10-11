package com.example.demo.service;

import com.example.demo.entity.Product;

public interface ProductService {
    public Product createProduct(Product product);
    public Product getProductById(Long id);
}