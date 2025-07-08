package com.mutsa.shoppingmall.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 장바구니 상품 추가 요청 DTO
 */
@Getter
public class CartItemAddRequest {
    @Schema(description = "추가할 상품 ID", example = "1", required = true)
    private Long productId;

    @Schema(description = "수량", example = "2", required = true)
    private Integer quantity;
} 