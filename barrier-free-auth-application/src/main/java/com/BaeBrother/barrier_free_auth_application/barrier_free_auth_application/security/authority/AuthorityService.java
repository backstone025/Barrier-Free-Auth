package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    public List<Authority> findAllByAccountId(Long accountId) {
        return authorityRepository.findAllByAccountId(accountId);
    }
}
