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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("유저 없음: " + username);
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword()) // DB의 '0000'
                .roles("USER")
                .build();
    }
}
