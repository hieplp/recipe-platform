package com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CancelRegisterOtpConfirmationCommand {
    @TargetAggregateIdentifier
    private String otpId;
    private String modifiedBy;
}
