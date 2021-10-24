package com.jpa.develop.domain.user.exception;

import com.jpa.develop.common.BusinessException;
import com.jpa.develop.common.ErrorCode;

public class UserIdDuplicateException extends BusinessException {

    public UserIdDuplicateException(String message) {
        super(message, ErrorCode.DUPLICATE_USER_ID);
    }

}
