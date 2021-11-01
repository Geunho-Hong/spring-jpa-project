package com.jpa.develop.dto.board.exception;

import com.jpa.develop.common.BusinessException;
import com.jpa.develop.common.ErrorCode;

public class BoardIsNotExistException extends BusinessException {

    public BoardIsNotExistException(String message) {
        super(message, ErrorCode.BOARD_IS_NOT_EXIST);
    }
}
