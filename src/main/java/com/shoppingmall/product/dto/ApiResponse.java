package com.shoppingmall.product.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse <T>{

    private int status_code;
    private String message;
    private T data;
}
