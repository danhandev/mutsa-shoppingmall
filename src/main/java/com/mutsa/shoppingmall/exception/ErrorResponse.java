package com.mutsa.shoppingmall.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * API 에러 응답 포맷 DTO
 */
@Getter
@Builder
public class ErrorResponse {
    /** 에러 코드 */
    private String errorCode;
    /** 에러 메시지 */
    private String message;
    /** HTTP 상태 코드 */
    private int status;
    /** 에러 발생 시각 */
    private LocalDateTime timestamp;
    /** 요청 경로 */
    private String path;
} 