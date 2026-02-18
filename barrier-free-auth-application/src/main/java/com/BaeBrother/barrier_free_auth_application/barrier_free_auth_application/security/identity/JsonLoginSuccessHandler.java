package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity.token.TokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;

@Component
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 토큰 생성
        String token = null;
        try {
            token = tokenService.createToken(authentication);
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 응답 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON 바디 설정
        String json = String.format(
                "{\"accessToken\": \"%s\", \"tokenType\": \"Bearer\"}",token
        );
        response.getWriter().write(json);
    }
}
