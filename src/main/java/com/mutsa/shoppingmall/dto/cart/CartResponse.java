package com.mutsa.shoppingmall.dto.cart;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

/**
 * 장바구니 전체 응답 DTO
 */
@Getter
@Builder
public class CartResponse {
    /** 장바구니에 담긴 상품 목록 */
    private List<CartItemResponse> cartItems;
    /** 장바구니 전체 상품 개수 */
    private Integer cartTotalItems;
    /** 장바구니 전체 금액(원) */
    private Integer cartTotalPrice;
} 