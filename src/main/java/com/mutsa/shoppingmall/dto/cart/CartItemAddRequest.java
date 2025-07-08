package com.mutsa.shoppingmall.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 장바구니 상품 추가 요청 DTO
 */
@Getter
public class CartItemAddRequest {
    @NotNull(message = "상품 ID는 필수입니다.")
    @Schema(description = "추가할 상품 ID", example = "1", required = true)
    private Long productId;

    @NotNull(message = "수량은 필수입니다.")
    @Min(value = 1, message = "수량은 1 이상이어야 합니다.")
    @Schema(description = "수량", example = "2", required = true)
    private Integer quantity;
} 