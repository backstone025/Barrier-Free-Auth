package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.Account;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Account account = accountRepository.findByLoginId(loginId);

        if (account == null) {
            throw new UsernameNotFoundException("유저 없음: " + loginId);
        }

        return User.builder()
                .username(account.getLoginId())
                .password(account.getPassword())
                .roles(account.getAccountType())
                .build();
    }
}
