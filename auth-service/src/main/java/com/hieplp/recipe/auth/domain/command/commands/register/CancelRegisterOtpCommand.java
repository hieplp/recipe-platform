package com.hieplp.recipe.auth.domain.command.commands.register;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CancelRegisterOtpCommand {
    @TargetAggregateIdentifier
    @NonNull
    private String otpId;
    @NonNull
    private String userId;
}
