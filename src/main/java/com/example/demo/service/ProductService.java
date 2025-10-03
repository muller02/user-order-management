package com.example.demo.service;

import com.example.demo.dto.ProductDTO;

public interface ProductService {
    public void saveProduct();
    public ProductDTO getProduct(String productId);
}
