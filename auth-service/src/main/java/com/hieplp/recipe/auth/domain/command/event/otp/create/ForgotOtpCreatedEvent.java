package com.hieplp.recipe.auth.domain.command.event.otp.create;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ForgotOtpCreatedEvent extends OtpCreatedEvent {
}
