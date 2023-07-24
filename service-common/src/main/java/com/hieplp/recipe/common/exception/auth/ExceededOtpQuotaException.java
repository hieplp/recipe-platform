package com.hieplp.recipe.common.exception.auth;

import com.hieplp.recipe.common.exception.BaseException;

public class ExceededOtpQuotaException extends BaseException {
    public ExceededOtpQuotaException(String message) {
        super(message);
    }
}
