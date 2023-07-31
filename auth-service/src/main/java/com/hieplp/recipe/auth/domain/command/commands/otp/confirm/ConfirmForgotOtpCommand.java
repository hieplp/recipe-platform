package com.hieplp.recipe.auth.domain.command.commands.otp.confirm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ConfirmForgotOtpCommand extends ConfirmOtpCommand {

}
