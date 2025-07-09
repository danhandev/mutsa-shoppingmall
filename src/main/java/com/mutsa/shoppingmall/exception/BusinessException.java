package com.mutsa.shoppingmall.exception;

/**
 * 비즈니스 예외의 최상위 클래스 (모든 커스텀 예외의 부모)
 */
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
} 