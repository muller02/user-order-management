package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;

public interface ProductService {
    public List<Product> getAllProducts();
    public ProductDTO getProductById(Long productId);
    public Product createProduct(Product product);
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO);
    public void deleteProduct(Long productId);
}