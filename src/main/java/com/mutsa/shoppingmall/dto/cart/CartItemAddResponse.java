package com.mutsa.shoppingmall.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

/**
 * 장바구니 상품 추가 응답 DTO
 */
@Getter
@Builder
public class CartItemAddResponse {
    @Schema(description = "장바구니 항목 ID", example = "1")
    private Long cartItemId;

    @Schema(description = "상품 ID", example = "1")
    private Long productId;

    @Schema(description = "수량", example = "2")
    private Integer quantity;
} 