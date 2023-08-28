package com.egin.springsecurity_2023_c5_e1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic()
                .and()
                .authorizeRequests()
//                .anyRequest().authenticated()
//                .anyRequest().permitAll()  // permitAll herhangi bir authentication'a sahip olmadığınızı söyler
                                    // permitAll'da auth yoksa sistem cevabı verir.
                                    // eğer yanlış bir kullanıcı username'i veya password'ü girilirse çalışmaz
//                .anyRequest().hasAuthority("read")  // belirtilen authority'ye sahip olanlar erişebilir
//                .anyRequest().hasAnyAuthority("read")
//                .anyRequest().hasRole("ADMIN")
//                .anyRequest().hasAnyRole("MANAGEMET", "ADMIN")

//                .anyRequest().authenticated()
//                .anyRequest().access("isAuthenticated() and hasAuthority('read')")  // SpEL -> Authorization rules
                .mvcMatchers("/demo").hasAuthority("read")
                .anyRequest().authenticated()

                .and().build();

        // matcher methpd + authorization rule
        // 1. Which mathcer methods should you use and how (anyRequest(), mvcMatchers(), antMatchers(), regexMathcers() )
        // 2. how to apply different authorization rules

        // 401 -> authentication failed
        // 403 -> authorization failed


    }

    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();

        var user = User
                .withUsername("ahmet")
                .password(passwordEncoder().encode("123456"))
                .authorities("read")
//                .roles("ADMIN")   // equivalent with and authority named ROLE_ADMIN
                .build();
        // teknik anlamda role ve authority aynı şey




        var user2 = User
                    .withUsername("laur")
                    .password(passwordEncoder().encode("123456"))
                    .authorities("write")
//                    .roles("MANAGEMENT")
                    .build();
        uds.createUser(user);
        uds.createUser(user2);
        return uds;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




}
