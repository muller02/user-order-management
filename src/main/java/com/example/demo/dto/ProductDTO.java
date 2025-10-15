package com.example.demo.dto;

import lombok.Builder;

@Builder
public record ProductDTO(Long productId, String productName, Integer productPrice, Integer productStock) {
} 