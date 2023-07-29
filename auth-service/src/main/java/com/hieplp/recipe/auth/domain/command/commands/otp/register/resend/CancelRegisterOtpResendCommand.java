package com.hieplp.recipe.auth.domain.command.commands.otp.register.resend;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CancelRegisterOtpResendCommand {
    @TargetAggregateIdentifier
    private String otpId;
}
