package com.hieplp.recipe.common.command.commands.user.create;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CancelUserCreationCommand {
    @TargetAggregateIdentifier
    private String userId;
}
