package com.hieplp.recipe.auth.domain.command.commands.otp.history.create;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateOtpHistoryCommand {
    @TargetAggregateIdentifier
    private String otpHistoryId;
    private String otpId;
    private String otpCode;
    private byte status;
    private byte type;
    private String createdBy;
}
