package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.dto.ProductCreateDTO;
import com.example.demo.dto.ProductPatchDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductResponseDTO getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found => " + id));
                
        return product.toDto();
    }

    @Override
    public ProductResponseDTO create(ProductCreateDTO productCreateDTO) {
        
        // User 검색 & orElseThrow를 통한 User 가공
        User user = userRepository.findById(productCreateDTO.createdBy())
                .orElseThrow(() -> new RuntimeException("user not found => " + productCreateDTO.createdBy()));

        // DTO -> Entity -> DB 저장
        Product product = productCreateDTO.toEntity(user);
        Product saved = productRepository.save(product);

        // Entity -> DTO
        return saved.toDto();
    }

    @Override
    @Transactional
    public ProductResponseDTO update(Long id, ProductResponseDTO productDTO) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found => " + id));
        
        /*
        Update fields
        @Transactional 영속성 자동 반영으로 flush -> save 불필요
         */
        existingProduct.setName(productDTO.name());
        existingProduct.setPrice(productDTO.price());
        existingProduct.setStock(productDTO.stock());

       return existingProduct.toDto();
    }

    @Override
    @Transactional
    public ProductResponseDTO update(Long id, ProductPatchDTO patchDTO) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found => " + id));

        // Update only the stock field
        /* 
         * @Transactional => existingProduct 영속성 자동 반영
         * JPA Dirty Checking => 변경이 감지되면 자동 UPDATE
        */
        if(patchDTO.stock()!=null)
                existingProduct.setStock(patchDTO.stock());

        return existingProduct.toDto();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Check if the product exists
        // 삭제 후 영향 받은 row 체크도 가능 => int deletedCount = productRepository.deleteByProductId(productId);
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found => " + id));

        productRepository.delete(existingProduct);
    }
}