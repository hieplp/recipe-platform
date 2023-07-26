package com.hieplp.recipe.common.exception.auth;

import com.hieplp.recipe.common.exception.BaseException;

public class IssuedOtpException extends BaseException {
    public IssuedOtpException(String msg) {
        super(msg);
    }
}
