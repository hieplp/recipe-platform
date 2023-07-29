package com.hieplp.recipe.auth.domain.command.event.otp.register.create;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterOtpCreatedEvent {
    private String otpId;
    private String otpCode;
    private Byte status;
    private Byte type;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String userId;
    private LocalDateTime issuedAt;
    private LocalDateTime expiredAt;
}
