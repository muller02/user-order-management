package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
// JPAAuditing을 위한 EntityListener 등록
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 일반 로그인
    @Column(nullable = false, unique = true)
    private String email;
    private String password; // 암호화 + nullable

    // 소셜 로그인
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
    private String providerId;
    
    @Column(nullable = false)
    private String name;
    private String picture;

    // 사용자 권한 ROLE_USER, ROLE_ADMIN
    // User에 직접 속하지 않는 컬렉션 값 타입 ( JPA가 별도 테이블로 관리 )
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>(); // 초기화로 null 방지

    // save() 시 자동으로 시간 저장
    // Auditing 활성화 필요
    @CreatedDate
    private LocalDateTime createdAt;

    // 해당 유저가 등록한 상품들
    @OneToMany(mappedBy = "createdBy")
    private List<Product> products = new ArrayList<>(); // 초기화로 null 방지
}