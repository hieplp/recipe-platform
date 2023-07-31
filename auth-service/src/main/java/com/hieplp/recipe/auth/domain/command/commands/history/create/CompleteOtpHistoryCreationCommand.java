package com.hieplp.recipe.auth.domain.command.commands.history.create;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompleteOtpHistoryCreationCommand {
    @TargetAggregateIdentifier
    private String otpHistoryId;
    private String modifiedBy;
}
