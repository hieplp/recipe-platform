package com.hieplp.recipe.auth.command.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompleteRegistrationOtpCommand {
    @TargetAggregateIdentifier
    private final String otpId;
    private String userId;
}

