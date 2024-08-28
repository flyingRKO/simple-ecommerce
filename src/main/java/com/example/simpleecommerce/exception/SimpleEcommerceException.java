package com.example.simpleecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SimpleEcommerceException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    public SimpleEcommerceException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.message = null;
    }

    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        } else {
            return String.format("%s. %s", errorCode.getMessage(), message);
        }
    }
}
