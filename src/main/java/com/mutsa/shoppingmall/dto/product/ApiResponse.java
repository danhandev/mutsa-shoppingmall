package com.mutsa.shoppingmall.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter 
@AllArgsConstructor
public class ApiResponse <T>{

    private int status_code;
    private String message;
    private T data;
}
