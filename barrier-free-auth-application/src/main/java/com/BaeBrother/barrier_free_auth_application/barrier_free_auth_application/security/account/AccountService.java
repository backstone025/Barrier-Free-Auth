package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Account account = accountRepository.findByUsername(authentication.getName());
            return account.getId();
        }
        return null;
    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }

    public Long saveMainAccount(MainAccount account) {
        if(account.getUsername() != null && account.getUsername() != null) {
            MainAccount savedAccount = accountRepository.save(account);
            if (savedAccount != null) {
                return savedAccount.getId();
            }
        }
        return null;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElse(null);
    }
}
