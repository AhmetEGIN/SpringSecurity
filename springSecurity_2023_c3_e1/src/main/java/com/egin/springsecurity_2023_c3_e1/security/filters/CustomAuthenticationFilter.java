package com.egin.springsecurity_2023_c3_e1.security.filters;

import com.egin.springsecurity_2023_c3_e1.security.authentication.CustomAuthentication;
import com.egin.springsecurity_2023_c3_e1.security.manager.CustomAuthenticationManager;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;

    public CustomAuthenticationFilter(CustomAuthenticationManager customAuthenticationManager) {
        this.customAuthenticationManager = customAuthenticationManager;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // create an authentication object which is not yet authenticated
        // Delegate the authentication object to do manager
        // get back the authentication from the manager
        // if the object authenticated then send request to the next filter in the chain

        String key = String.valueOf(request.getHeader("key"));
        CustomAuthentication customAuthentication = new CustomAuthentication(false, key);

        var a = customAuthenticationManager.authenticate(customAuthentication);
        if (a.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request, response);  // only when authentication worked

        }

/*
* Burada gerçekleşen olayları sıralayalım:
* Öncelikle gönderdiğimiz Request AuthentizationFilter'da doFilter metoduna girecek.
* 35. satırda request'te header kısmında verdiğimiz 'key' değerini okuyoruz.
* Daha sonra bu key değeri ile authenticate edilmemiş bir tane customAuthentication nesnesi oluşturuyoruz.
* Sonra AuthenticationManager sınıfında authenticate metodunu customAuthentication parametresini vererek çalıştırıyoruz
* AuthenticationManager sınıfı da Provider üzerinden 'support()' metodu üzerinden gönderdiğimiz authentication nesnesinin
* geçerli olup olmadığını kontrol ediyor.
* Gönderdiğimiz authentication nesnesi uygun değilse BadCredentialsException fırlatılıyor
*
* Eğer authentication nesnesi uygunsa Provider üzerindeki 'authhenticate()' metodu çalıştırılıyor ve kullanıcının authenticate
* olup olamayacağı belirleniyor.
* Eğer request'in header'ında bulunan key değeri bizim uygulamamızın key değerine eşit değilse BadCredentialsException fırlatılıyor
* Key değeri eşitse kullanıcı authenticate ediliyor.
*
* daha sonra tekrardan AuthenticationFilter'a dönüyoruz.
* isAuthenticated() metodu ile kullanıcının authenticate olup olmadığını kontrol ediyoruz.
* Eğer authenticate olmuşsa bunu security context'in içerisine atıyoruz ve bir sonraki filter'a aktarıyoruz
*
* */



    }
}
