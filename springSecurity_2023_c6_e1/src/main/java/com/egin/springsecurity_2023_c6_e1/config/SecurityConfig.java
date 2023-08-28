package com.egin.springsecurity_2023_c6_e1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService() {


        var uds = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("ahmet")
                .password(passwordEncoder().encode("12345"))
                .authorities("read")
                .build();
        var user2 = User.withUsername("laur")
                .password(passwordEncoder().encode("12345"))
                .authorities("write")
                .build();
        var user3 = User.withUsername("muhammet")
                .password(passwordEncoder().encode("12345"))
                .authorities("write", "read")
                .build();
        uds.createUser(user1);
        uds.createUser(user2);
        uds.createUser(user3);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        httpSecurity
//                .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/test1").authenticated()
//                    .requestMatchers("/test2").hasAuthority("read")
//                    .requestMatchers("/test3").permitAll()
//                    )
//                ;
//
//        return httpSecurity.build();

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                //csrf().disablet() dememizin şöyle bir sebebi var:
                // CSRF(Cross Site Request Forgery),  herhangi bir web uygulamasında oturum açmış bir kullanıcının oturumunu
                // o web sitesinin açığından faydalanarak kullanıcının isteği dışında ,
                // saldırganın sanki o kullanıcıymış gibi erişim sağlayıp işlemler yapmasıdır.
                // Web sitelerindeki bu açıklar,
                // çoğunlukla GET requestleri ve SESSION işlemlerinin doğru kontrol edilememesi gibi durumlarda ortaya çıkan açıklardır
                // Bizim burada devre dışı bırakmamızın sebebi uygulamamızı test etmek istememizdir.
                // Eğer disable olarak işaretlemezsek, Postman'de POST, DELETE, PUT gibi işlemleri yapmamıza izin vermez.
                // Tabiki gerçek hayat uygulamalarında bunu etkin bırakmamız gerekmektedir.
                .authorizeHttpRequests()
//                .requestMatchers("/test1").authenticated()
//                .requestMatchers("/test2").hasAuthority("read")
//                .requestMatchers("/test3").permitAll()   // permitAll()' da ya NoAuth olacak ya da kullanıcı Authenticated olacak
//                                                            // eğer kullanıcı authenticated değilse 401 fırlatılır
//                .anyRequest().authenticated()
                
                .requestMatchers(HttpMethod.GET,"/api/**").hasAuthority("read")
                .anyRequest().authenticated()
                .and()
                .httpBasic(Customizer.withDefaults())
                .build();


    }
// /api/anything/*/something   ---> /api/anything/abc/something
//                                   /api/anything/xyz/something

}
