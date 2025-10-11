package com.example.demo.dto;

import com.example.demo.entity.Product;

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
//TODO: Change class to Record class
public class ProductDTO {
    
    private Long productId;
    private String productName;
    private Integer productPrice;
    private Integer productStock;

    public Product toEntity(){
        return Product.builder()
                .productId(this.productId)
                .productName(this.productName)
                .productPrice(this.productPrice)
                .productStock(this.productStock)
                .build();
    }
}