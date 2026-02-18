package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    public List<Authority> findAllByAccountId(Long accountId) {
        return authorityRepository.findAllByAccountId(accountId);
    }
}
