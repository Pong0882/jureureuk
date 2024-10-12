package com.jureureuk.jureureuk.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

// @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// http
// .authorizeHttpRequests(authorize -> authorize
// .requestMatchers("/", "/UI/**").permitAll() // 특정 경로는 인증 없이 접근 허용
// .anyRequest().authenticated() // 나머지는 인증 필요
// )
// .oauth2Login(oauth2 -> oauth2
// .defaultSuccessUrl("/loginSuccess") // 로그인 성공 시 이동할 경로
// .permitAll())

// .logout(logout -> logout
// .logoutSuccessUrl("/") // 로그아웃 성공 시 이동할 경로
// .permitAll());

// return http.build();
// }
// }
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
                        .anyRequest().permitAll() // 모든 경로를 인증 없이 접근 가능하게 설정
                )
                .csrf().disable(); // CSRF 보안을 비활성화 (로컬 테스트용)

        return http.build();
    }
}
