package com.will.loja.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomException extends RuntimeException {

    public CustomException() {

    }

    public CustomException(String message) {
        super(message);
        System.out.println(message);
    }

    public CustomException(String message, Throwable cause) {
        this(MessageExceptionHandler.getMessage(message, cause));
    }

    public CustomException(Throwable cause) {
        this(MessageExceptionHandler.getMessage(cause));
    }

    public CustomException(String message, List<String> arguments) {
        this(MessageExceptionHandler.getMessage(message, arguments));
    }

    public CustomException(String message, String argument) {
        this(MessageExceptionHandler.getMessage(message, argument));
    }

    public CustomException(EnumCustomException customMessage, Object... arguments) {
        this(MessageExceptionHandler.getMessage(customMessage, arguments));
    }

    public CustomException(String customMessage, Object... arguments) {
        this(MessageExceptionHandler.getMessage(customMessage, arguments));
    }
}
