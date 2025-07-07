package com.mutsa.shoppingmall.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse<T> {
    private int statusCode;
    private String message;
    private T data;
} 