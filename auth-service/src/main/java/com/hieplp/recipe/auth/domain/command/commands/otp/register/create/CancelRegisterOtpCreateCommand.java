package com.hieplp.recipe.auth.domain.command.commands.otp.register.create;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CancelRegisterOtpCreateCommand {
    @TargetAggregateIdentifier
    @NonNull
    private String otpId;
    @NonNull
    private String userId;
}
