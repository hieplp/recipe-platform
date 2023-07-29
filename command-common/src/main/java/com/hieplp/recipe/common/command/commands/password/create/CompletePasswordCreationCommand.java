package com.hieplp.recipe.common.command.commands.password.create;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompletePasswordCreationCommand {
    @TargetAggregateIdentifier
    private String userId;
}
