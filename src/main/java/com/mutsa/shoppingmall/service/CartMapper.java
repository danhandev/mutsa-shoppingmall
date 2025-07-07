package com.mutsa.shoppingmall.service;

import com.mutsa.shoppingmall.domain.Cart;
import com.mutsa.shoppingmall.domain.CartItem;
import com.mutsa.shoppingmall.dto.cart.CartItemResponse;
import com.mutsa.shoppingmall.dto.cart.CartResponse;
import com.mutsa.shoppingmall.dto.cart.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 장바구니 관련 엔티티 → DTO 변환 Mapper
 */
@Component
public class CartMapper {
    /**
     * Cart 엔티티를 CartResponse DTO로 변환
     * @param cart 장바구니 엔티티
     * @return CartResponse DTO
     */
    public CartResponse toCartResponse(Cart cart) {
        List<CartItemResponse> cartItems = cart.getCartItems().stream()
                .map(this::toCartItemResponse)
                .collect(Collectors.toList());

        // cartTotalItems, cartTotalPrice를 한 번의 루프에서 계산
        int cartTotalItems = 0;
        int cartTotalPrice = 0;
        for (CartItemResponse item : cartItems) {
            cartTotalItems += item.getQuantity();
            cartTotalPrice += item.getProduct().getPrice() * item.getQuantity();
        }

        return CartResponse.builder()
                .cartItems(cartItems)
                .cartTotalItems(cartTotalItems)
                .cartTotalPrice(cartTotalPrice)
                .build();
    }

    /**
     * CartItem 엔티티를 CartItemResponse DTO로 변환
     */
    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        return CartItemResponse.builder()
                .cartItemId(cartItem.getCartItemId())
                .product(toProductResponse(cartItem.getProduct()))
                .quantity(cartItem.getQuantity())
                .build();
    }

    /**
     * Product 엔티티를 ProductResponse DTO로 변환
     */
    public ProductResponse toProductResponse(com.mutsa.shoppingmall.domain.Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .content(product.getContent())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
    }
} 