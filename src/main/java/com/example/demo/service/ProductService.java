package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductPatchDTO;
import com.example.demo.entity.Product;

public interface ProductService {
    public List<Product> getAllProducts();
    public ProductDTO getProductById(Long productId);
    public Product createProduct(Product product);
    // PUT
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO);
    // PATCH
    public ProductDTO updateProduct(Long productId, ProductPatchDTO patchDTO);
    public void deleteProduct(Long productId);
}