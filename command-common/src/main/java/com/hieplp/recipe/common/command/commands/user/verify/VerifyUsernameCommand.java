package com.hieplp.recipe.common.command.commands.user.verify;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class VerifyUsernameCommand {
    @TargetAggregateIdentifier
    @NonNull
    private String userId;
    @NonNull
    private String username;
}
