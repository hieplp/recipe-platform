package com.hieplp.recipe.common.command.commands.password.update;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdatePasswordCommand {
    @TargetAggregateIdentifier
    private String userId;
    private byte[] password;
    private byte[] salt;
}
