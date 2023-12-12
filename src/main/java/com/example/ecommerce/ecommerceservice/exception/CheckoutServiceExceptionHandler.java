package com.example.ecommerce.ecommerceservice.exception;

import com.example.ecommerce.ecommerceservice.service.CheckoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class CheckoutServiceExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(
            ApiException ex, HttpServletRequest request) {
        log.error("[Checkout Api Exception Handling] CheckoutServiceExeption request path : {}, exception information: {}", request.getRequestURI(), ex.getErrorMessage());
        List<String> details = new ArrayList<>();
        details.add(ex.getErrorCode());
        details.add(ex.getErrorMessage());
        details.add(request.getRequestURI());
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, HttpServletRequest request) {
        log.error("[Checkout Api Exception Handling] handleAccessDeniedException request path : {}, exception information: {}", request.getRequestURI(), ex.getCause());
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        details.add(request.getRequestURI());
        return new ResponseEntity<>(details, HttpStatus.UNAUTHORIZED);    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeExeption(
            Exception ex, HttpServletRequest request) {
        log.error("[Checkout Api Exception Handling] RuntimeExeption request path : {}, exception information: {}", request.getRequestURI(), ex.getCause());
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        details.add(request.getRequestURI());
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

@Override
@Nullable
protected ResponseEntity<Object> handleHandlerMethodValidationException(
        HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("[Checkout Api Exception Handling] IllegalArgumentException  exception information: {}", ex.getCause());
    List<String> details = new ArrayList<>();
    details.add("Invalid Input");
    details.add(ex.getMessage());
    return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
}
}
