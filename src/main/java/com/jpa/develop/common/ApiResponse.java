package com.jpa.develop.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private Integer status;

    private String message;

    private T data;

}


