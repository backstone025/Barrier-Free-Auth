package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ACCOUNT_TYPE")
public abstract class Account {
    public Account() {
    }

    public Account(Long id, String accountType, String loginId, String password, Long tokenVersion) {
        this.id = id;
        this.accountType = accountType;
        this.loginId = loginId;
        this.password = password;
        this.tokenVersion = tokenVersion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ACCOUNT_TYPE", insertable = false, updatable = false)
    private String accountType;
    private String loginId;
    private String password;
    private Long tokenVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String username) {
        this.loginId = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getTokenVersion() {
        return tokenVersion;
    }

    public void setTokenVersion(Long tokenVersion) {
        this.tokenVersion = tokenVersion;
    }
}
