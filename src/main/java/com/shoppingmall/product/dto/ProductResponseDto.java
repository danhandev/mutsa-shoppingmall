package com.shoppingmall.product.dto;

import com.shoppingmall.product.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {

    private Long id;
    private String name;
    private String content;
    private Integer price;
    private String imageUrl;
    private Integer stockQuantity;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        this.stockQuantity = product.getStockQuantity();

    }
}
