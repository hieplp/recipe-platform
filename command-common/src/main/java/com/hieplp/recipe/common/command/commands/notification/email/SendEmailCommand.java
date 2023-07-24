package com.hieplp.recipe.common.command.commands.notification.email;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Map;

@Data
@Builder
public class SendEmailCommand {
    @TargetAggregateIdentifier
    private String email;
    private String action;
    private Map<String, String> params;
    private String createdBy;
}
