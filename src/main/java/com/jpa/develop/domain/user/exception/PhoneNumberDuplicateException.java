package com.jpa.develop.domain.user.exception;

import com.jpa.develop.common.BusinessException;
import com.jpa.develop.common.ErrorCode;

public class PhoneNumberDuplicateException extends BusinessException {

    public PhoneNumberDuplicateException(String message) {
        super(message,ErrorCode.DUPLICATE_PHONE_NUM);
    }

}
