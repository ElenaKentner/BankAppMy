package com.example.bankapp.security;

import com.example.bankapp.entity.Client;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ClientProvider {
    public Client getCurrentClient() {
        return ((CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .client();
    }
}
