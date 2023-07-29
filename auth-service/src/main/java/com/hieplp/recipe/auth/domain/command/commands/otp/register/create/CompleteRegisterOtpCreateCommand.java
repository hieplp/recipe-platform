package com.hieplp.recipe.auth.domain.command.commands.otp.register.create;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompleteRegisterOtpCreateCommand {
    @TargetAggregateIdentifier
    @NonNull
    private final String otpId;
    @NonNull
    private String userId;
}

