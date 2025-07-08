package com.mutsa.shoppingmall.controller;

import com.mutsa.shoppingmall.dto.CommonResponse;
import com.mutsa.shoppingmall.dto.cart.CartResponse;
import com.mutsa.shoppingmall.dto.cart.CartItemAddRequest;
import com.mutsa.shoppingmall.dto.cart.CartItemAddResponse;
import com.mutsa.shoppingmall.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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

    /**
     * 장바구니에 상품 추가 API
     * @param email 사용자 이메일
     * @param request 상품ID, 수량
     * @return 추가된 CartItem 정보
     */
    @Operation(summary = "장바구니 상품 추가", description = "장바구니에 상품을 추가합니다.",
        responses = {
            @ApiResponse(responseCode = "201", description = "장바구니에 상품을 추가했습니다.",
                content = @Content(schema = @Schema(implementation = CartItemAddResponse.class)))
        })
    @PostMapping("/items")
    public ResponseEntity<CommonResponse<CartItemAddResponse>> addCartItem(
            @Parameter(description = "로그인한 사용자 이메일", required = true, example = "user@example.com")
            @RequestParam String email,
            @RequestBody CartItemAddRequest request) {
        CartItemAddResponse response = cartService.addCartItem(email, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<CartItemAddResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("장바구니에 상품을 추가했습니다.")
                        .data(response)
                        .build());
    }
} 