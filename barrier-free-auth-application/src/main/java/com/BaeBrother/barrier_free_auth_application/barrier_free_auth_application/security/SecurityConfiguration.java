package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public SecurityFilterChain fileterChain(HttpSecurity http) throws Exception {
        // h2 console 혀용
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated());
        // 기본적으로 제공되는 로그인 폼 사용
        http.formLogin(withDefaults());
        // http 기본 인증 기본값
        http.httpBasic(withDefaults());
        // scrf 차단
        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }
}
