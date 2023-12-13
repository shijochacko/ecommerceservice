package com.example.ecommerce.ecommerceservice.config;

import com.example.ecommerce.ecommerceservice.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UsernamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        String value = getUserCredentials().get(username);
        if(null != value){
            if (passwordEncoder.matches(pwd, value)) {
                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities("ROLE_USER"));
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }else{
             throw new BadCredentialsException("No user registered with this details!");
        }

    }

    private List<GrantedAuthority> getGrantedAuthorities(String authority) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public Map<String, String> getUserCredentials(){
        Map<String, String> userMap = new HashMap<>();
        userMap.put("user", passwordEncoder.encode("user"));
        return userMap;
    }



}
