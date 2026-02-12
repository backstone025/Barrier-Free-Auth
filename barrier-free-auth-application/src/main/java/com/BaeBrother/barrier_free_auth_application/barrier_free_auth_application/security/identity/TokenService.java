package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.AccountService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority.Authority;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TokenService {
    @Autowired
    AccountService accountService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    private JwtEncoder jwtEncoder;

    public String createToken(Authentication authentication) {
        String accountType = accountService.getAccountType();
        Long accountId = accountService.getCurrentAccountId();
        List<Authority> authorities = authorityService.findAllByAccountId(accountId);

        var claims = JwtClaimsSet.builder()
                .issuer("server")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60*30))
                .subject(authentication.getName())
                .claim("AccountType", accountType)
                .claim("Authorities", createScope(authorities))
                .build();
        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();

    }

    private Object createScope(List<Authority> authorities) {
        return authorities.stream()
                .map(a -> a.getUserAction()+":"+a.getUserScope())
                .collect(Collectors.toList());
    }
}