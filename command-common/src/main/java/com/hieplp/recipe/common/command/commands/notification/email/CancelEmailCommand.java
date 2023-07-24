package com.hieplp.recipe.common.command.commands.notification.email;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CancelEmailCommand {
    @TargetAggregateIdentifier
    private String logId;
}
