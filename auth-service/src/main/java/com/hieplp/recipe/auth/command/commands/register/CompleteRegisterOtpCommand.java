package com.hieplp.recipe.auth.command.commands.register;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompleteRegisterOtpCommand {
    @TargetAggregateIdentifier
    @NonNull
    private final String otpId;
    @NonNull
    private String userId;
}

