package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority.Authority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationValidator {
    public boolean hasAccess(String token, List<Authority> authorities) {
        // 테스트용
        return true;
    }
}
