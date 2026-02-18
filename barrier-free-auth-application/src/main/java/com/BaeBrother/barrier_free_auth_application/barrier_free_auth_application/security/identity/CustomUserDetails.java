package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private final Long accountId;
    private final String accountType;

    // 생성자에서 Account entity 바로 받는다.
    public CustomUserDetails(Account account, Collection<? extends GrantedAuthority> authorities) {
        // 부모에게 필요한 정보만 넘겨준다.
        super(account.getLoginId(), account.getPassword(), authorities);

        this.accountId = account.getId();
        this.accountType = account.getAccountType();
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getAccountType() {
        return accountType;
    }
}
