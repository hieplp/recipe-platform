package com.hieplp.recipe.auth.config.handler;

import com.hieplp.recipe.common.enums.response.ErrorCode;
import com.hieplp.recipe.common.exception.BadRequestException;
import com.hieplp.recipe.common.jooq.exception.NotFoundException;
import com.hieplp.recipe.common.payload.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order()
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CommonResponse> handleNotFoundException(NotFoundException e) {
        log.error("NotFoundException: {}", e.getMessage());
        return new ResponseEntity<>(new CommonResponse(ErrorCode.NOT_FOUND), HttpStatus.OK);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CommonResponse> handleBadRequestException(BadRequestException e) {
        log.error("BadRequestException: {}", e.getMessage());
        return new ResponseEntity<>(new CommonResponse(ErrorCode.BAD_REQUEST), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handleException(Exception e) {
        log.error("Exception: ", e);
        return new ResponseEntity<>(new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR), HttpStatus.OK);
    }
}
