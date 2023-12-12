package com.example.ecommerce.ecommerceservice.exception;

public class UnauthorizedException  extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
