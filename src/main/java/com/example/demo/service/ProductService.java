package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.dto.ProductCreateDTO;
import com.example.demo.dto.ProductPatchDTO;
import com.example.demo.entity.Product;

public interface ProductService {
    public List<Product> getAllProducts();
    public ProductResponseDTO getById(Long idd);
    public ProductResponseDTO create(ProductCreateDTO productCreateDTO);
    // PUT
    public ProductResponseDTO update(Long id, ProductResponseDTO productDTO);
    // PATCH
    public ProductResponseDTO update(Long id, ProductPatchDTO patchDTO);
    public void delete(Long id);
}