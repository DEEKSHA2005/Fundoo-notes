package com.bridgelabz.fundoonotes.util;

import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    public String generateToken(Long userId) {
        return "TOKEN_" + userId;
    }

    public Long decodeToken(String token) {
        return Long.parseLong(token.replace("TOKEN_", ""));
    }
}