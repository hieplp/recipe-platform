package com.hieplp.recipe.auth.domain.command.commands.otp.create;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CancelOtpCreationCommand {
    @TargetAggregateIdentifier
    @NonNull
    private String otpId;
}
