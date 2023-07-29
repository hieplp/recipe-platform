package com.hieplp.recipe.auth.domain.command.commands.otp.history.create;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompleteOtpHistoryCreationCommand {
    @TargetAggregateIdentifier
    private String otpId;
    private byte status;
}
