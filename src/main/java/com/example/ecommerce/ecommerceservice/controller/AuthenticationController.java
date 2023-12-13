package com.example.ecommerce.ecommerceservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthenticationController {
   @GetMapping("/user")
   public ResponseEntity<String> getUserDetailsAfterLogin(Authentication authentication) {
      return ResponseEntity.ok(authentication.getName());

   }
}
