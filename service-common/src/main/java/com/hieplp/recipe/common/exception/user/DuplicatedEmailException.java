package com.hieplp.recipe.common.exception.user;

import com.hieplp.recipe.common.exception.BaseException;

public class DuplicatedEmailException extends BaseException {
    public DuplicatedEmailException(String message) {
        super(message);
    }
}
