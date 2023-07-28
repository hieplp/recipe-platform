package com.hieplp.recipe.auth.domain.command.commands.register;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class RequestRegisterOtpCreateCommand {
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
    private String email;
    @NonNull
    private String userId;
    @NonNull
    private String password;
    @NonNull
    private LocalDateTime issuedAt;
    @NonNull
    private LocalDateTime expiredAt;
}
