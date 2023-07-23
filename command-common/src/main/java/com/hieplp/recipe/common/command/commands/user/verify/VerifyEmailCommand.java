package com.hieplp.recipe.common.command.commands.user.verify;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class VerifyEmailCommand {
    @TargetAggregateIdentifier
    private String userId;
    private String email;
}
