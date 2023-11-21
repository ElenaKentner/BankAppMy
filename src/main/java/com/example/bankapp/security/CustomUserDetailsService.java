package com.example.bankapp.security;

import com.example.bankapp.entity.Client;
import com.example.bankapp.repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public CustomUserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository
                .findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Client not found"));
        return new CustomUserDetails(client);
    }
}
