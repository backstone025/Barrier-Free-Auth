package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor // 모든 field값을 포함한 생성자
public class AuthorityBinder {
    private Role role;
    private LocalDate expiredDate;
}
