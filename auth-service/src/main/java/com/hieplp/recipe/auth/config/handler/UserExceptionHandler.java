package com.hieplp.recipe.auth.config.handler;


import com.hieplp.recipe.common.enums.response.ErrorCode;
import com.hieplp.recipe.common.exception.user.DuplicatedEmailException;
import com.hieplp.recipe.common.exception.user.DuplicatedUsernameException;
import com.hieplp.recipe.common.payload.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DuplicatedUsernameException.class)
    public ResponseEntity<CommonResponse> handleDuplicatedUsernameException(DuplicatedUsernameException e) {
        log.error("DuplicatedUsernameException: {}", e.getMessage());
        return new ResponseEntity<>(new CommonResponse(ErrorCode.DUPLICATED_USERNAME), HttpStatus.OK);
    }

    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<CommonResponse> handleDuplicatedEmailException(DuplicatedEmailException e) {
        log.error("DuplicatedEmailException: {}", e.getMessage());
        return new ResponseEntity<>(new CommonResponse(ErrorCode.DUPLICATED_EMAIL), HttpStatus.OK);
    }
}
