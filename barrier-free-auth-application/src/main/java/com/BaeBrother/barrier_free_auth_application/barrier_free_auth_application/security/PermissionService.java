package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority.UserAction;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority.UserScope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PermissionService {

    public PermissionService() {
    }

    public void verify(ServiceMappingTable methodCode, Jwt jwt) throws AccessDeniedException {
        String accountType = jwt.getClaims().get("AccountType").toString();
        List<AuthPair> authorities = getAuthoritiesFromToken(jwt);

        UserAction action = methodCode.getAction();
        UserScope scope = methodCode.getScope();

        if (!methodCode.getAccessableAccountType().contains(accountType)) {
            throw new AccessDeniedException("접근할 수 없는 계정 타입입니다.");
        }
        if (!authorities.contains(new AuthPair(action, scope))) {
            throw new AccessDeniedException("권한을 가지고 있지 않습니다.");
        }
    }

    public List<AuthPair> getAuthoritiesFromToken(Jwt jwt) {
        List<String> rawAuthorities = (List<String>) jwt.getClaims().get("Authorities");
        return rawAuthorities.stream()
                .map(auth -> {
                    String[] split = auth.split(":");
                    return new AuthPair(
                            UserAction.valueOf(split[0]),
                            UserScope.valueOf(split[1])
                    );
                })
                .toList();
    }
}

record AuthPair(UserAction action, UserScope scope) {
}
