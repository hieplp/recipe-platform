package com.hieplp.recipe.common.command.commands.notification.email;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Map;

@Data
@Builder
public class SendEmailCommand {
    @TargetAggregateIdentifier
    @NonNull
    private final String logId;
    @NonNull
    private final String sendTo;
    @NonNull
    private final String action;
    @NonNull
    private final String createdBy;
    private final Map<String, String> params;
}
