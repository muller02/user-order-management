package com.example.demo.dto;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductStatus;
import com.example.demo.entity.User;

import lombok.Builder;
/*
 * Product 생성 시, createdBy 필드에 userId(Long) 필요
 */
@Builder
public record ProductCreateDTO(Long id, 
                        String name, 
                        Integer price,
                        String description, 
                        Integer stock,
                        String status,
                        Long createdBy,
                        String createdAt,
                        String updatedAt,
                        Boolean isDeleted) {

    // 검증된 UserId 필요
    public Product toEntity(User user) {
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .description(this.description)
                .stock(this.stock)
                .status(ProductStatus.valueOf(this.status)) // Enum 변환
                .createdBy(user)
                .build();
    }
} 