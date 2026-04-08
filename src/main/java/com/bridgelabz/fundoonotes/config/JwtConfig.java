package com.bridgelabz.fundoonotes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration.ms}")
    private long expiration;

    public String getSecretKey() {
        return secretKey;
    }

    public long getExpiration() {
        return expiration;
    }
}