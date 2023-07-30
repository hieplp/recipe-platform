package com.hieplp.recipe.auth.domain.command.event.otp.create;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ForgotOtpCreatedEvent {
    private String otpId;
    private String otpCode;
    private String sendTo;
    private String createdBy;
    private LocalDateTime issuedAt;
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
    private Byte type;
    private Byte status;
}
