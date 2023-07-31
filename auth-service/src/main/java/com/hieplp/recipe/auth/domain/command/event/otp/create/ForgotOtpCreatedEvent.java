package com.hieplp.recipe.auth.domain.command.event.otp.create;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ForgotOtpCreatedEvent extends OtpCreatedEvent {
}
