package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    public String createToken() {
        String token = UUID.randomUUID().toString();
        return token;
    }
}
