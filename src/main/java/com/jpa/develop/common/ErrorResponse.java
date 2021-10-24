package com.jpa.develop.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private Integer status;

    private String message;

    private String code;

    @Builder
    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

}
