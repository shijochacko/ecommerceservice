package com.example.ecommerce.ecommerceservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException{

    private String errorCode;
    private String errorMessage;


    public ApiException(String errorMessage) {
        this.errorMessage =  errorMessage;
    }
    public ApiException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApiException(String message, String errorCode, String errorMessage) {
        super(message);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApiException(String message, Throwable cause, String errorCode, String errorMessage) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApiException(Throwable cause, String errorCode, String errorMessage) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode, String errorMessage) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }



}
