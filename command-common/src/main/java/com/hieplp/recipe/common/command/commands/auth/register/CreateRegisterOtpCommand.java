package com.hieplp.recipe.common.command.commands.auth.register;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateRegisterOtpCommand {
    @TargetAggregateIdentifier
    private String otpId;
    private String username;
    private String email;
    private Byte otpStatus;
}

