package com.mutsa.shoppingmall.controller;
//api 요청 받음 : service 호출

import com.mutsa.shoppingmall.dto.product.ApiResponse;
import com.mutsa.shoppingmall.dto.product.ProductListResponseDto;
import com.mutsa.shoppingmall.dto.product.ProductResponseDto;
import com.mutsa.shoppingmall.service.ProductService;
// import com.mutsa.shoppingmall.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 전체 상품 조회
    @GetMapping
    public ResponseEntity<ApiResponse<ProductListResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productService.getAllProducts().stream()
                .map(ProductResponseDto::new)
                .toList();

        ApiResponse<ProductListResponseDto> response = new ApiResponse<>(
                200,
                "상품 전체 조회를 성공했습니다.",
                new ProductListResponseDto(products)
        );

        return ResponseEntity.ok(response);
    }

    // 개별 상품 조회
    @GetMapping("/{product_id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProductById(@PathVariable Long product_id) {
        return productService.getProductById(product_id)
                .map(product -> ResponseEntity.ok(
                        new ApiResponse<>(200, "상품 조회를 성공했습니다.", new ProductResponseDto(product))
                ))
                .orElse(ResponseEntity.status(404).body(
                        new ApiResponse<>(404, "해당 상품은 존재하지 않습니다.", null)
                ));
    }

    // 상품 이름으로 검색
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<ProductListResponseDto>> searchProducts(@RequestParam String name) {
        List<ProductResponseDto> products = productService.getProductByName(name).stream()
                .map(ProductResponseDto::new)
                .toList();

        ApiResponse<ProductListResponseDto> response = new ApiResponse<>(
                200,
                "상품명으로 검색한 결과입니다.",
                new ProductListResponseDto(products)
        );

        return ResponseEntity.ok(response);
    }
}
