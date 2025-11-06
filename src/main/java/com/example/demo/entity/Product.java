package com.example.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.dto.ProductResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
// createdAt, updatedAt 자동 설정을 위한 Auditing 설정
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Soft Delete 설정
@SoftDelete(columnName = "is_deleted")
@Table(name = "products")
//TODO: 검색 필터 where = "is_deleted = false" 추가 필요
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

    @ManyToOne(fetch = FetchType.LAZY)
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

    public ProductResponseDTO toDto(){
        return ProductResponseDTO.builder()
            .id(id)
            .name(name)
            .price(price)
            .description(description)
            .stock(stock)
            .status(status.name())
            .createdBy(createdBy != null ? createdBy.getName() : null)
            .createdAt(createdAt != null ? createdAt.toString() : null)
            .updatedAt(updatedAt != null ? updatedAt.toString() : null)
            .build();
    }
}