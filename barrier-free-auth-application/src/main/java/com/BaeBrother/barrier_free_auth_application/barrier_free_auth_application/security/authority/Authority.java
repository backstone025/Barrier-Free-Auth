package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority;

import jakarta.persistence.*;

@Entity
@Table(name = "AUTHORITY")
public class Authority {
    public Authority() {
    }

    public Authority(Long id, Long accountId, String userAction, String userScope) {
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
    private String userAction;
    @Enumerated(EnumType.STRING)
    private String userScope;

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

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public String getUserScope() {
        return userScope;
    }

    public void setUserScope(String userScope) {
        this.userScope = userScope;
    }
}
