package com.jpa.develop.domain.user.exception;

import com.jpa.develop.common.BusinessException;
import com.jpa.develop.common.ErrorCode;

/**
 * @Project     : toy-spring-jpa-pj
 * @FileName    : LoginFailException
 * @Date        : 2021-12-23
 * @author      : user
 * @description :
 * 
 */
public class LoginFailException extends BusinessException {

    public LoginFailException(String message) {
        super(message, ErrorCode.USR_LOGIN_FAIL);
    }

}
