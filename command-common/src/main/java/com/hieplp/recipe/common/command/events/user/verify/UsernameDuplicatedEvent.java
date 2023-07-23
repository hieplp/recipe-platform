package com.hieplp.recipe.common.command.events.user.verify;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UsernameDuplicatedEvent {
    private String userId;
    private String username;
}
