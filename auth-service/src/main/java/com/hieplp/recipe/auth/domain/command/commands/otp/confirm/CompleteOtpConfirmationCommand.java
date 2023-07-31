package com.hieplp.recipe.auth.domain.command.commands.otp.confirm;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompleteOtpConfirmationCommand {
    @TargetAggregateIdentifier
    private String otpId;
}
