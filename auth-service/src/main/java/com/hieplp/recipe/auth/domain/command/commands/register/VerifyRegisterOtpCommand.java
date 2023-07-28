package com.hieplp.recipe.auth.domain.command.commands.register;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class VerifyRegisterOtpCommand {
    @TargetAggregateIdentifier
    @NonNull
    private final String otpId;
    @NonNull
    private final String otpCode;
    @NonNull
    private final Boolean verified;
}
