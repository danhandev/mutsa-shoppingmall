package com.shoppingmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductListResponseDto {
    private List<ProductResponseDto> products;
}
