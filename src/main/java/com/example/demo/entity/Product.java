package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.demo.dto.ProductDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer price;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private Integer stock;

    // enum class ProductStatus { AVAILABLE, SOLD_OUT }
    private ProductStatus status;

    // TODO: 관계 매핑 필요 ( ManyToOne )
    // private Category caregory;

    @JoinColumn(name = "user_id")
    private User createdBy;

    // 추후 주문 내역 추가
    // private List<Order> orders;

    // Auditing
    // 혹은 DEFAULT_CURRENT_TIMESTAMP 가능
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // Soft Delete
    @Column(nullable = false)
    private Boolean isDeleted = false;

    public ProductDTO toDto(){
        return ProductDTO.builder()
            .id(id)
            .name(name)
            .price(price)
            .description(description)
            .stock(stock)
            .build();
    }
}