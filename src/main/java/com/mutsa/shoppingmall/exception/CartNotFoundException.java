package com.mutsa.shoppingmall.exception;

/**
 * 장바구니가 존재하지 않을 때 발생하는 예외
 */
public class CartNotFoundException extends BusinessException {
    public CartNotFoundException() {
        super(ErrorCode.CART_NOT_FOUND);
    }
} 