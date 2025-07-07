package com.mutsa.shoppingmall.controller;

import com.mutsa.shoppingmall.dto.CommonResponse;
import com.mutsa.shoppingmall.dto.cart.CartResponse;
import com.mutsa.shoppingmall.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 장바구니 관련 API 컨트롤러
 */
@Tag(name = "Cart", description = "장바구니 API")
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * 내 장바구니 조회 API
     * @param email 로그인한 사용자 이메일 (실무에서는 인증 정보에서 추출)
     * @return 장바구니 정보
     */
    @Operation(summary = "내 장바구니 조회", description = "로그인한 사용자의 장바구니 정보를 조회합니다.")
    @GetMapping
    public ResponseEntity<CommonResponse<CartResponse>> getMyCart(
            @Parameter(description = "로그인한 사용자 이메일", required = true, example = "user@example.com")
            @RequestParam String email) {
        CartResponse cartResponse = cartService.getMyCartByEmail(email);
        return ResponseEntity.ok(
            CommonResponse.<CartResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("장바구니 조회를 성공했습니다.")
                .data(cartResponse)
                .build()
        );
    }
} 