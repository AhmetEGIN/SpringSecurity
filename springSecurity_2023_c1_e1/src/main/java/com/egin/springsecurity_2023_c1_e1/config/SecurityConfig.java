package com.egin.springsecurity_2023_c1_e1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();
//  Kullanıcı ve şifre bilgilerini farklı bir veri kaynağından almak için kullanılır
//  Burada bir kullanıcının bilgilerini veriyoruz ve bunu context'e atıyoruz.
//        Bu sayede SPring Security kullanıcı hakkında bilgi sahibi olabiliyor.
//        kısaca UserDetailsService Spring Security ile bizim uygulamamızdaki kullanıcılar arasından bir sözleşme gibidir.



        var user = User.withUsername("ahmet")
                .password("123456")
                .authorities("read")
                .build();
        uds.createUser(user);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();

    }


}
