package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.AccountService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority.Authority;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TokenService {
    @Autowired
    AccountService accountService;
    @Autowired
    AuthorityService authorityService;

    public String createToken() {
        Long accountId = accountService.getCurrentAccountId();
        List<Authority> authorities = authorityService.findAllByAccountId(accountId);
    }
}
