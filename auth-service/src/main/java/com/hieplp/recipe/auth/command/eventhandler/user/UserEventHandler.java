package com.hieplp.recipe.auth.command.eventhandler.user;

import com.hieplp.recipe.common.command.events.user.verify.EmailDuplicatedEvent;
import com.hieplp.recipe.common.command.events.user.verify.EmailVerifiedEvent;
import com.hieplp.recipe.common.command.events.user.verify.UsernameDuplicatedEvent;
import com.hieplp.recipe.common.command.events.user.verify.UsernameVerifiedEvent;
import com.hieplp.recipe.common.enums.response.ErrorCode;
import com.hieplp.recipe.common.jooq.exception.NotFoundException;
import com.hieplp.recipe.common.payload.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserEventHandler {
    // -------------------------------------------------------------------------
    // XXX Verify username
    // -------------------------------------------------------------------------
    @EventHandler
    private void handle(UsernameVerifiedEvent event) {
        log.info("UsernameVerifiedEvent: {}", event);
    }

    @EventHandler
    private ResponseEntity<CommonResponse> handle(UsernameDuplicatedEvent event) {
        log.info("UsernameDuplicatedEvent: {}", event);
        throw new NotFoundException("Username is duplicated");
    }

    @ExceptionHandler
    public ResponseEntity<CommonResponse> handleNotFoundException(Exception e) {
        log.error("NotFoundException: {}", e.getMessage());
        return new ResponseEntity<>(new CommonResponse(ErrorCode.NOT_FOUND), HttpStatus.OK);
    }


    // -------------------------------------------------------------------------
    // XXX Verify email
    // -------------------------------------------------------------------------
    @EventHandler
    private void handle(EmailVerifiedEvent event) {
        log.info("EmailVerifiedEvent: {}", event);
    }

    @EventHandler
    private void handle(EmailDuplicatedEvent event) {
        log.info("EmailDuplicatedEvent: {}", event);
    }
}
