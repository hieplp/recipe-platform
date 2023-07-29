package com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompleteRegisterOtpConfirmationCommand {
    @TargetAggregateIdentifier
    private String otpId;
}
