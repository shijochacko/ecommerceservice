package com.example.ecommerce.ecommerceservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig  {

   @Bean
   SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(authorize -> authorize.anyRequest()
                .authenticated()).httpBasic(Customizer.withDefaults());
    return http.build();
    }
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() throws Exception {
       return (web) -> web.ignoring()
               .requestMatchers("/api/v1/checkout","/actuator/*","/swagger/api/v1/checkout");
    }

}
