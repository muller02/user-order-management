package com.example.demo.dao.impl;

import org.springframework.stereotype.Service;
import com.example.demo.dao.ProductDAO;
import com.example.demo.model.ProductEntity;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductDAOImpl implements ProductDAO {
    
    ProductRepository productRepository;

    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity){
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity getProduct(String productId){
        return productRepository.findById(productId).orElse(null);
    }
}