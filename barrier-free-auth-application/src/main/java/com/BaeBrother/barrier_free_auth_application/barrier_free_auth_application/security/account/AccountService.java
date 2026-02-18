package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity.CustomUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Long getCurrentAccountId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
            return customUserDetails.getAccountId();
        }
        return null;
    }

    public String getLoginId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }

    public String getAccountType() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
            return customUserDetails.getAccountType();
        }
        return null;
    }

    public Long saveMainAccount(MainAccount account) {
        if (account.getLoginId() != null && account.getLoginId() != null) {
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

    public Long getCurrentVersion(long accountId) {
        return accountRepository.findById(accountId).get().getTokenVersion();
    }

    @Transactional
    public Long updateTokenVersion(Long accountId) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            Long tokenVersion = account.getTokenVersion() + 1;
            account.setTokenVersion(tokenVersion);
            return tokenVersion;
        }else {
            throw new AccountNotFoundException();
        }
    }
}
