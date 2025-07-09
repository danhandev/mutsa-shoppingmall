package com.mutsa.shoppingmall.exception;

/**
 * 장바구니 상품이 존재하지 않을 때 발생하는 예외
 */
public class CartItemNotFoundException extends BusinessException {
    public CartItemNotFoundException() {
        super(ErrorCode.CART_ITEM_NOT_FOUND);
    }
} 