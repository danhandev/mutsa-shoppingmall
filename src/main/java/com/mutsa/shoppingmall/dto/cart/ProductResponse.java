package com.mutsa.shoppingmall.dto.cart;

import lombok.Builder;
import lombok.Getter;

/**
 * 장바구니 상품 정보 응답 DTO
 */
@Getter
@Builder
public class ProductResponse {
    /** 상품 ID */
    private Long id;
    /** 상품명 */
    private String name;
    /** 상품 설명 */
    private String content;
    /** 상품 가격(원) */
    private Integer price;
    /** 상품 이미지 URL */
    private String imageUrl;
} 