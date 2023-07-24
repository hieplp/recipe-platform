package com.hieplp.recipe.common.exception;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 5070192011710943669L;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }
}