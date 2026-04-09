package com.bridgelabz.fundoonotes.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RedisService {

    private final Map<String, Long> cache = new HashMap<>();

    // Save token
    public void saveToken(String token, Long userId) {
        cache.put(token, userId);
    }

    // Get userId
    public Long getUserId(String token) {
        return cache.get(token);
    }
}