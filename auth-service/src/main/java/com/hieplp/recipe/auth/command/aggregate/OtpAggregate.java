package com.hieplp.recipe.auth.command.aggregate;

import com.hieplp.recipe.auth.command.commands.CreateRegistrationOtpCommand;
import com.hieplp.recipe.auth.command.event.register.RegisterOtpCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Slf4j
public class OtpAggregate {
    @AggregateIdentifier
    private String otpId;
    private String username;
    private String email;

    protected OtpAggregate() {
    }

    @CommandHandler
    public OtpAggregate(CreateRegistrationOtpCommand command) {
        log.info("Create registration otp command: {}", command);
        // Validate Command Here

        //
        var otpCreatedEvent = new RegisterOtpCreatedEvent();
        BeanUtils.copyProperties(command, otpCreatedEvent);
        AggregateLifecycle.apply(otpCreatedEvent);
    }

    @EventSourcingHandler
    public void on(RegisterOtpCreatedEvent event) {
        this.otpId = event.getOtpId();
        this.email = event.getEmail();
        this.username = event.getUsername();
    }
}
