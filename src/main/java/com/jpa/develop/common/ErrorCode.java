package com.jpa.develop.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_ERROR(400,"C001","Invalid Input Value"),
    SERVER_ERROR(500,"S001","Server Error"),

    // Member
    DUPLICATE_USER_ID(400,"U001","Email is Duplication"),
    DUPLICATE_PHONE_NUM(400,"U002","PhoneNumber is Duplication"),

    // Board
    BOARD_IS_NOT_EXIST(400,"B001","Board is not exist");

    private final Integer status;
    private final String message;
    private final String code;

    ErrorCode(final Integer status, final String message, final String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

}
