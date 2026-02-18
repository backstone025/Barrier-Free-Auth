package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity.token.TokenFilter;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

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
    public SecurityFilterChain fileterChain(HttpSecurity http, JsonLoginSuccessHandler jsonLoginSuccessHandler, TokenFilter tokenFilter) throws Exception {
        // h2 console 혀용
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated());
        // h2 사용을 위해 프레임 관련 보안 끄기
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        // csrf 차단
        http.csrf(csrf -> csrf.disable());

//        // 세션 정책을 stateless로 설정 -> 로그아웃시 토큰 새로 갱신
//        http.sessionManagement(session ->
//                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.formLogin(form -> form.disable());
        // 토큰 반환하도록 설정
        http.formLogin(form -> form
                .successHandler(jsonLoginSuccessHandler));
        // 커스텀 필터 적용
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

//        // OAuth2 resource server 활성화하기 -> 충돌 위험성으로 보류
//        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
    
    // JWT configuration
    @Bean
    public KeyPair keyPair() {
        try{
            var keyPair = KeyPairGenerator.getInstance("RSA");
            keyPair.initialize(2048);
            return keyPair.generateKeyPair();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Bean
    public RSAKey rsaKey(KeyPair keyPair) {
        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        var jwkSet = new JWKSet(rsaKey);

        var jwkSource = new JWKSource(){

            @Override
            public List<JWK> get(JWKSelector jwkSelector, SecurityContext securityContext) throws KeySourceException {
                return jwkSelector.select(jwkSet);
            }
        };
        return jwkSource;
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder
                .withPublicKey(rsaKey.toRSAPublicKey())
                .build();
    }

    @Bean
    public JwtEncoder jwtEncoder(RSAKey rsaKey) {
        return new NimbusJwtEncoder(jwkSource(rsaKey));
    }
}
