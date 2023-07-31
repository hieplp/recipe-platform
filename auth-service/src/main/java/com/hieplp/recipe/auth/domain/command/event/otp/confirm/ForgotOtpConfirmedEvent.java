package com.hieplp.recipe.auth.domain.command.event.otp.confirm;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ForgotOtpConfirmedEvent extends OtpConfirmedEvent {
}
