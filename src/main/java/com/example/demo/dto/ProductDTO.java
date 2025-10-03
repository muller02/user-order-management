package com.example.demo.dto;

import com.example.demo.model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDTO {
    
    String productId;
    String productName;
    Integer productPrice;
    Integer productStock;

    public ProductEntity toEntity(){
        return ProductEntity.builder()
                .productId(this.productId)
                .productName(this.productName)
                .productPrice(this.productPrice)
                .productStock(this.productStock)
                .build();
    }
}