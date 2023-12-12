package com.example.ecommerce.ecommerceservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.swing.text.html.HTML;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends ApiException{

    public ServiceException(String errorCode, String errorMessage) {
        super(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), errorMessage);
    }

    public ServiceException(String message, String errorCode, String errorMessage) {
        super(message, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), errorMessage);
    }

    public ServiceException(String message, Throwable cause, String errorCode, String errorMessage) {
        super(message, cause, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), errorMessage);
    }

    public ServiceException(Throwable cause, String errorCode, String errorMessage) {
        super(cause, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), errorMessage);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode, String errorMessage) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode, errorMessage);
    }
}
