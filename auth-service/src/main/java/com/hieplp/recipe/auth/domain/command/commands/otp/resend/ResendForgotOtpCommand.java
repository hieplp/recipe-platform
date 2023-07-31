package com.hieplp.recipe.auth.domain.command.commands.otp.resend;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ResendForgotOtpCommand {
    @TargetAggregateIdentifier
    private String otpId;
}
