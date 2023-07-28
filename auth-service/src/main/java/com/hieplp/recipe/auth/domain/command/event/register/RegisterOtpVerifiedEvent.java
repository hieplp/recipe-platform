package com.hieplp.recipe.auth.domain.command.event.register;

public class RegisterOtpVerifiedEvent {
    private String otpId;
    private String otpCode;
    private String userId;
}
