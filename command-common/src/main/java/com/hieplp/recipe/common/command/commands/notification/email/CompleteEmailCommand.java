package com.hieplp.recipe.common.command.commands.notification.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CompleteEmailCommand {
    @TargetAggregateIdentifier
    private String logId;
}
