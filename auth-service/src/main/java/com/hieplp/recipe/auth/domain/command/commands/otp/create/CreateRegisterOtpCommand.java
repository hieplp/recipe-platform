package com.hieplp.recipe.auth.domain.command.commands.otp.create;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Builder
@Data
public class CreateRegisterOtpCommand {
    @TargetAggregateIdentifier
    @NonNull
    private final String otpId;
    @NonNull
    private String otpCode;
    @NonNull
    private String username;
    @NonNull
    private String fullName;
    @NonNull
    private String sendTo;
    @NonNull
    private String userId;
    @NonNull
    private String password;
    @NonNull
    private LocalDateTime issuedAt;
    @NonNull
    private LocalDateTime expiredAt;
}

