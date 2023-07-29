package com.hieplp.recipe.auth.domain.command.commands.otp.register.confirm;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class ConfirmRegisterOtpCommand {
    @TargetAggregateIdentifier
    private final String otpId;
    private final String otpCode;
}
