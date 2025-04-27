package com.imentor.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/v1/auth/register",
                        "/api/v1/auth/login",
                        "/swagger-ui.html",
                        "/swagger-ui/**", // <-- allow swagger resources
                        "/v3/api-docs/**")
                .permitAll() // Allow login and register without JWT
                .anyRequest().authenticated() // Protect all other routes
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Add your JWT filter before
                                                                                         // UsernamePasswordAuthenticationFilter
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
