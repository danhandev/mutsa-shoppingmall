package com.mutsa.shoppingmall.exception;

import org.springframework.http.HttpStatus;

/**
 * API 공통 및 Cart/Product 관련 에러 코드 정의
 */
public enum ErrorCode {
    // 공통 에러
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED("UNAUTHORIZED", "로그인 정보가 유효하지 않습니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_ACCESS("UNAUTHORIZED_ACCESS", "해당 리소스에 대한 접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    INVALID_REQUEST("INVALID_REQUEST", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    MISSING_REQUIRED_FIELD("MISSING_REQUIRED_FIELD", "필수 항목이 누락되었습니다.", HttpStatus.BAD_REQUEST),
    INVALID_TYPE("INVALID_TYPE", "입력 형식이 올바르지 않습니다.", HttpStatus.UNPROCESSABLE_ENTITY),

    // Cart 관련 에러
    CART_NOT_FOUND("CART_NOT_FOUND", "장바구니가 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    CART_ITEM_NOT_FOUND("CART_ITEM_NOT_FOUND", "해당 장바구니 상품이 존재하지 않습니다.", HttpStatus.NOT_FOUND),

    // Product 관련 에러
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", "해당 상품은 존재하지 않습니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public int getStatus() {
        return httpStatus.value();
    }
} 