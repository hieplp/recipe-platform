package com.hieplp.recipe.auth.domain.command.commands.otp.confirm;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@SuperBuilder
public class ConfirmOtpCommand {
    @TargetAggregateIdentifier
    private final String otpId;
    private final String otpCode;
    private String userId;
}
