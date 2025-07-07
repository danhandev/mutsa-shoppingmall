package com.mutsa.shoppingmall.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * 전역 예외 처리기 (API 에러 응답 일원화)
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 비즈니스 예외 처리 (커스텀 예외)
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex, HttpServletRequest request) {
        ErrorCode code = ex.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(code.getCode())
                .message(code.getMessage())
                .status(code.getStatus())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

    /**
     * 그 외 모든 예외 처리 (서버 에러)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        // springdoc-openapi 내부 요청은 기본 처리로 넘김
        if (request.getRequestURI().startsWith("/v3/api-docs")) {
            return null; // Spring 기본 처리로 넘김 (Swagger 정상 동작)
        }
        ErrorCode code = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(code.getCode())
                .message(code.getMessage())
                .status(code.getStatus())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }
} 