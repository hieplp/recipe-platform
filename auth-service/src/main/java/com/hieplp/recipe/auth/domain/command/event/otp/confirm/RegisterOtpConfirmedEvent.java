package com.hieplp.recipe.auth.domain.command.event.otp.confirm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RegisterOtpConfirmedEvent extends OtpConfirmedEvent {
}
