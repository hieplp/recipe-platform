package com.hieplp.recipe.auth.domain.command.commands.otp.confirm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConfirmRegisterOtpCommand extends ConfirmOtpCommand {
}
