package com.example.ecommerce.ecommerceservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestInvalidException extends ApiException{

    public RequestInvalidException(String errorMessage) {
        super(String.valueOf(HttpStatus.BAD_REQUEST), errorMessage);
    }

    public RequestInvalidException(String message, String errorCode, String errorMessage) {
        super(message,String.valueOf(HttpStatus.BAD_REQUEST), errorMessage);
    }

    public RequestInvalidException(String message, Throwable cause, String errorCode, String errorMessage) {
        super(message, cause, String.valueOf(HttpStatus.BAD_REQUEST), errorMessage);
    }

    public RequestInvalidException(Throwable cause, String errorCode, String errorMessage) {
        super(cause, String.valueOf(HttpStatus.BAD_REQUEST), errorMessage);
    }

    public RequestInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode, String errorMessage) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode, errorMessage);
    }
}
