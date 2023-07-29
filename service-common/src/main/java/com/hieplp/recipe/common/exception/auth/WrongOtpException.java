package com.hieplp.recipe.common.exception.auth;

import com.hieplp.recipe.common.exception.BaseException;

public class WrongOtpException extends BaseException {
    public WrongOtpException(String message) {
        super(message);
    }
}
