package com.hieplp.recipe.auth.command.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CancelRegistrationOtpCommand {
    @TargetAggregateIdentifier
    private String otpId;
    private String userId;
}
