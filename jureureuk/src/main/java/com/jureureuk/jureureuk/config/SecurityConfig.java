package com.jureureuk.jureureuk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/", "/UI/**", "/nickname/save", "/profile",
                                                                "/images/**", "/refrigerator/api/**")
                                                .permitAll() // 특정 경로는 인증 없이 접근 허용
                                                .anyRequest().authenticated() // 나머지는 인증 필요
                                )
                                .oauth2Login(oauth2 -> oauth2
                                                .defaultSuccessUrl("/nickname", true) // 로그인 성공 후 이동할 경로
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutSuccessUrl("/") // 로그아웃 성공 후 이동할 경로
                                                .permitAll())
                                .csrf(csrf -> csrf
                                                .ignoringRequestMatchers("/nickname/save", "/refrigerator/api/**") // CSRF
                                                                                                                   // 보호
                                                                                                                   // 비활성화
                                );

                return http.build();
        }
}
