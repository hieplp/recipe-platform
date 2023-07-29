package com.hieplp.recipe.common.enums.response;

public enum ErrorCode implements ResponseCode {
    BAD_REQUEST("4000", "Bad request"),
    DUPLICATED_EMAIL("4001", "Email is already existed"),
    DUPLICATED_USERNAME("4002", "Username is already existed"),
    QUOTA_EXCEEDED("4003", "Quota exceeded"),
    NOT_FOUND("4004", "Not found"),
    UNAUTHORIZED("4005", "Unauthorized"),
    INACTIVE_USER("4006", "Inactive user"),
    WRONG_OTP("4007", "Wrong OTP"),
    EXPIRED_OTP("4008", "Expired OTP"),
    ISSUED_OTP("4009", "Issued OTP"),
    INTERNAL_SERVER_ERROR("5000", "Internal server error"),
    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}