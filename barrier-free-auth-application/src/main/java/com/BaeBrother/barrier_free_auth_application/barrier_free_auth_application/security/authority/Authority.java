package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority;

import jakarta.persistence.*;

@Entity
@Table(name = "AUTHORITY")
public class Authority {
    public Authority() {
    }

    public Authority(Long id, Long accountId, UserAction userAction, UserScope userScope) {
        this.id = id;
        this.accountId = accountId;
        this.userAction = userAction;
        this.userScope = userScope;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ACCOUNT_TYPE", insertable = false, updatable = false)
    private Long accountId;
    @Enumerated(EnumType.STRING)
    private UserAction userAction;
    @Enumerated(EnumType.STRING)
    private UserScope userScope;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public UserAction getUserAction() {
        return userAction;
    }

    public void setUserAction(UserAction userAction) {
        this.userAction = userAction;
    }

    public UserScope getUserScope() {
        return userScope;
    }

    public void setUserScope(UserScope userScope) {
        this.userScope = userScope;
    }
}
