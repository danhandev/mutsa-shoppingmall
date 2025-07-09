package com.shoppingmall.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 상품(Product) 엔티티
 * - 판매 가능한 상품 정보
 */
@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 상품명 */
    @Column(nullable = false, length = 100)
    private String name;

    /** 상품 설명 */
    @Column(length = 1000)
    private String content;

    /** 상품 가격 (단위: 원) */
    @Column(nullable = false)
    private Integer price;

    /** 상품 이미지 URL */
    @Column(length = 255)
    private String imageUrl;

    /** 재고 수량 */
    @Column(nullable = false)
    private Integer stockQuantity;

    /** 생성 일시 */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** 수정 일시 */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
