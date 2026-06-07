package com.deepak.gitinsight.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private RateLimiterFilter rateLimiterFilter;
    public SecurityConfig(RateLimiterFilter rateLimiterFilter) {
        this.rateLimiterFilter = rateLimiterFilter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            .addFilterBefore(rateLimiterFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
        
    }
}
