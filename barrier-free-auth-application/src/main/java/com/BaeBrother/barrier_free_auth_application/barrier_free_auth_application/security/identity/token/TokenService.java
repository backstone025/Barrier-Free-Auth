package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity.token;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.Account;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.AccountService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority.Authority;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority.AuthorityService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {
    private static final Logger log = LoggerFactory.getLogger(TokenService.class);
    @Autowired
    AccountService accountService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private JwtDecoder jwtDecoder;

    public String createToken(Authentication authentication) throws AccountNotFoundException {
        String accountType = accountService.getAccountType();
        Long accountId = accountService.getCurrentAccountId();
        List<Authority> authorities = authorityService.findAllByAccountId(accountId);
        Long tokenVersion = accountService.updateTokenVersion(accountId);

        var claims = JwtClaimsSet.builder()
                .issuer("server")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60*30))
                .subject(authentication.getName()) // 사용자 login id
                .claim("AccountId", accountId)
                .claim("AccountType", accountType)
                .claim("Authorities", createScope(authorities))
                .claim("Version", tokenVersion)
                .build();
        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }

    private Object createScope(List<Authority> authorities) {
        return authorities.stream()
                .map(a -> a.getUserAction()+":"+a.getUserScope())
                .collect(Collectors.toList());
    }

    public Jwt validateToken(String token) {
        try{
            // 기술적 검증(서명, 만료일, 형식)
            Jwt jwt = jwtDecoder.decode(token);
            Long accountId = jwt.getClaim("AccountId");
            Long tokenVersion = jwt.getClaim("Version");

            if(tokenVersion.longValue() < 0L){
                throw new JwtException("비정상적인 토큰 버전이 감지되었습니다.");
            }
            if(!tokenVersion.equals(accountService.getCurrentVersion(accountId))){
                throw new JwtException("토큰 권한이 일치하지 않습니다.");
            }
            return jwt;
        }catch (JwtException e){
            log.error("JWT 검증 실패 : {}", e.getMessage());
            return null;
        }
    }

    // 토큰 속 정보 꺼내올 때 사용할 정보를 담는다.
    public Authentication getAuthentication(Jwt jwt) {
        List<String> rawAthorities = jwt.getClaim("Authorities");
        List<SimpleGrantedAuthority> authorities = rawAthorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        Account account = accountService.getAccount(jwt.getClaim("AccountId"));

        CustomUserDetails customUserDetails = new CustomUserDetails(account, authorities);

        return new UsernamePasswordAuthenticationToken(customUserDetails, null, Collections.emptyList());
    }
}