package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductPatchDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("product not found => " + productId));
                
        return new ProductDTO(
                product.getProductId(), 
                product.getProductName(), 
                product.getProductPrice(), 
                product.getProductStock());
    }

    @Override
    public Product createProduct(Product product) {
            return productRepository.save(product);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {

        Product existingProduct = productRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("product not found => " + productId));
        
        /*
        Update fields
        @Transactional 영속성 자동 반영으로 flush -> save 불필요
         */
        existingProduct.setProductName(productDTO.productName());
        existingProduct.setProductPrice(productDTO.productPrice());
        existingProduct.setProductStock(productDTO.productStock());

       return existingProduct.toDto();
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long productId, ProductPatchDTO patchDTO) {

        Product existingProduct = productRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("product not found => " + productId));

        // Update only the stock field
        /* 
         * @Transactional => existingProduct 영속성 자동 반영
         * JPA Dirty Checking => 변경이 감지되면 자동 UPDATE
        */
        if(patchDTO.productStock()!=null)
                existingProduct.setProductStock(patchDTO.productStock());

        return existingProduct.toDto();
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        // Check if the product exists
        // 삭제 후 영향 받은 row 체크도 가능 => int deletedCount = productRepository.deleteByProductId(productId);
        Product existingProduct = productRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("product not found => " + productId));

        productRepository.delete(existingProduct);
    }
}