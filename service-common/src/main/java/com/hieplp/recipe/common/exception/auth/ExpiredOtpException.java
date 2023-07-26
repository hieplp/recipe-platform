package com.hieplp.recipe.common.exception.auth;

import com.hieplp.recipe.common.exception.BaseException;

public class ExpiredOtpException extends BaseException {
    public ExpiredOtpException(String msg) {
        super(msg);
    }
}
