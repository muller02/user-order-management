package com.example.demo.entity;

import com.example.demo.dto.ProductDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private Integer productPrice;
    private Integer productStock;

    public ProductDTO toDto(){
        return ProductDTO.builder()
            .productId(productId)
            .productName(productName)
            .productPrice(productPrice)
            .productStock(productStock)
            .build();
    }
}