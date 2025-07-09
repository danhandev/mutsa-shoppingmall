package com.mutsa.shoppingmall.dto.product;

import com.mutsa.shoppingmall.domain.Product;
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
        this.content = product.getContent() != null ? product.getContent() : "";
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl() != null ? product.getImageUrl() : "";
        this.stockQuantity = product.getStockQuantity();
    }
}
