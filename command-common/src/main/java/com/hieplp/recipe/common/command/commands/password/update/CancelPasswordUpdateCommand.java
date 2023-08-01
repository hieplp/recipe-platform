package com.hieplp.recipe.common.command.commands.password.update;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CancelPasswordUpdateCommand {
    @TargetAggregateIdentifier
    private String userId;
}
