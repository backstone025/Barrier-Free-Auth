package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority;


import java.time.LocalDate;

public class AuthorityBinder {
    public AuthorityBinder(Role role, LocalDate expiredDate) {
        this.role = role;
        this.expiredDate = expiredDate;
    }

    private Role role;
    private LocalDate expiredDate;

    public Role getRole() {
        return role;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }
}
