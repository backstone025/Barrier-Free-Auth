package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity.JsonLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 실제 비번 엔코딩할 경우
        //return new BCryptPasswordEncoder();

        // 테스트를 위해 NoOp을 사용
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain fileterChain(HttpSecurity http, JsonLoginSuccessHandler jsonLoginSuccessHandler) throws Exception {
        // h2 console 혀용
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated());
        // h2 사용을 위해 프레임 관련 보안 끄기
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        // scrf 차단
        http.csrf(csrf -> csrf.disable());

        // http 기본 인증 기본값
        http.httpBasic(withDefaults());
        // 토큰 반환하도록 설정
        http.formLogin(form -> form
                .successHandler(jsonLoginSuccessHandler));
        // OAuth2 resource server 활성화하기
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
