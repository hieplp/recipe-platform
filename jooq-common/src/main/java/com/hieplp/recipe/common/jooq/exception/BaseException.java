package com.hieplp.recipe.common.jooq.exception;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -128216908108589678L;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }
}