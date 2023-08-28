package com.egin.springsecurity_2023_c4_e1.config.filters;


import com.egin.springsecurity_2023_c4_e1.config.authentication.ApiKeyAuthentication;
import com.egin.springsecurity_2023_c4_e1.config.managers.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {


    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        CustomAuthenticationManager authenticationManager = new CustomAuthenticationManager(secretKey);

        var requestKey = request.getHeader("key");

        if (requestKey == null || "null".equals(requestKey)) {
            throw new BadCredentialsException("asd");
//            filterChain.doFilter(request, response);
        }

        var auth = new ApiKeyAuthentication(requestKey);

        try {
            var a = authenticationManager.authenticate(auth);
            if (a.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(a);
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        }

        filterChain.doFilter(request, response);

    }
}
