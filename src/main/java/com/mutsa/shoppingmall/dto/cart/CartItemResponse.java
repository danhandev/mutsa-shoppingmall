package com.mutsa.shoppingmall.dto.cart;

import lombok.Builder;
import lombok.Getter;

/**
 * 장바구니 항목(개별 상품) 응답 DTO
 */
@Getter
@Builder
public class CartItemResponse {
    /** 장바구니 항목 ID */
    private Long cartItemId;
    /** 상품 정보 */
    private ProductResponse product;
    /** 담긴 수량 */
    private Integer quantity;
} 