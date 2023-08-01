package com.hieplp.recipe.common.exception.auth;

import com.hieplp.recipe.common.exception.BaseException;

public class PasswordNotMatchException extends BaseException {
    public PasswordNotMatchException(String msg) {
        super(msg);
    }
}
