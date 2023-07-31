package com.hieplp.recipe.auth.domain.command.commands.otp.create;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateForgotOtpCommand {
    @TargetAggregateIdentifier
    private final String otpId;
    private final String otpCode;
    private final String sendTo;
    private final LocalDateTime issuedAt;
    private final LocalDateTime expiredAt;
}
