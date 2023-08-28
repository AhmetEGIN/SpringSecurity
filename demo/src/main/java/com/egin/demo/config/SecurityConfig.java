package com.egin.demo.config;

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

//        return http
//                .authorizeHttpRequests(auth ->
//                        auth
//                                .requestMatchers("/test2").permitAll()
//                                .requestMatchers("/test3").hasAuthority("read")
//                                .requestMatchers("/test1").authenticated()  // test1 authenticate olması yeterli
//
//                )
////                .authorizeHttpRequests(auth -> {
////                    auth.requestMatchers("/test1").authenticated()  // test1 authenticate olması yeterli
////                    .requestMatchers("/test2").permitAll();  //
//
////                    auth.anyRequest().authenticated();
////                })
//                .build();

//        return http
//                .authorizeHttpRequests()
//                .requestMatchers("/test1").authenticated()
//                .requestMatchers("/test2").hasAuthority("read")
//                .and().build();

        return http
                .httpBasic()
                .and()
                .authorizeRequests()
                .mvcMatchers("/test1").authenticated()
                .mvcMatchers("/test2").hasAuthority("read")
                .and().build();

    }

    @Bean
    public UserDetailsService userDetailsService() {
        var uds = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("ahmet")
                .password(passwordEncoder().encode("123456"))
                .authorities("read")
                .build();

        var user2 = User.withUsername("laur")
                .password(passwordEncoder().encode("123456"))
                .authorities("write")
                .build();

        uds.createUser(user1);
        uds.createUser(user2);

        return uds;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}