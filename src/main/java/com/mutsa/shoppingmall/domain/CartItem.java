package com.mutsa.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 장바구니 항목(CartItem) 엔티티
 * - 특정 장바구니에 담긴 개별 상품 단위
 * - 상품 + 수량 정보를 가짐
 */
@Entity
@Table(name = "cart_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    /** 소속 장바구니 (N:1) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    /** 담긴 상품 정보 (N:1) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /** 장바구니에 담긴 수량 */
    private Integer quantity;
} 