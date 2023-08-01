package com.hieplp.recipe.auth.common.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OtpEntity {
    private String otpId;
    private String otpCode;
    private String sendTo;
    private LocalDateTime issuedAt;
    private LocalDateTime expiredAt;
    private Byte status;
    private Byte type;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}
