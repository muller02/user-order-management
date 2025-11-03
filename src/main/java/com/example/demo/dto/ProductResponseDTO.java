package com.example.demo.dto;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductStatus;

import lombok.Builder;
@Builder
public record ProductResponseDTO(Long id, 
                        String name, 
                        Integer price,
                        String description, 
                        Integer stock,
                        String status,
                        String createdBy,
                        String createdAt,
                        String updatedAt) {

    // public Product toEntity() {
    //     return Product.builder()
    //             .id(this.id)
    //             .name(this.name)
    //             .price(this.price)
    //             .description(this.description)
    //             .stock(this.stock)
    //             .status(ProductStatus.valueOf(this.status)) // Enum 변환
    //             .createdBy() // User Entity 변환 필요 시
    //             .build();
    // }
} 