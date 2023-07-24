package com.hieplp.recipe.common.exception.user;

import com.hieplp.recipe.common.exception.BaseException;

public class DuplicatedUsernameException extends BaseException {
    public DuplicatedUsernameException(String message) {
        super(message);
    }
}
