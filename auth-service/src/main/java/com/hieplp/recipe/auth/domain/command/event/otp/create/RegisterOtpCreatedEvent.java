package com.hieplp.recipe.auth.domain.command.event.otp.create;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
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
    private String createdBy;
    private LocalDateTime createdAt;
}
