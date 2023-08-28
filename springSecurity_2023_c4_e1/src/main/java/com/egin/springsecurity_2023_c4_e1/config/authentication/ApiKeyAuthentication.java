package com.egin.springsecurity_2023_c4_e1.config.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

@RequiredArgsConstructor
public class ApiKeyAuthentication implements Authentication {

    private final String secretKey;


    private boolean authenticated;

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public boolean implies(Subject subject) {
        return Authentication.super.implies(subject);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }



    @Override
    public String getName() {
        return null;
    }
}
