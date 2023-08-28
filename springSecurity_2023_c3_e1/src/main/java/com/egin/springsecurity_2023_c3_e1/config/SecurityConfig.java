package com.egin.springsecurity_2023_c3_e1.config;

import com.egin.springsecurity_2023_c3_e1.security.filters.CustomAuthenticationFilter;
import com.egin.springsecurity_2023_c3_e1.security.manager.CustomAuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final CustomAuthenticationFilter customAuthenticationFilter;

    public SecurityConfig(
            CustomAuthenticationFilter customAuthenticationFilter
    ) {
        this.customAuthenticationFilter = customAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {



        return http
                .addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .build();


    }

}
