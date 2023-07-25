package com.hieplp.recipe.auth.command.event.register;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterOtpCreatedEvent {
    private String otpId;
    private String otpCode;
    private Byte status;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String userId;
    private LocalDateTime issuedAt;
    private LocalDateTime expiredAt;
}
