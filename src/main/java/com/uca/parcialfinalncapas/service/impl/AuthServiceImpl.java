package com.uca.parcialfinalncapas.service.impl;

import com.uca.parcialfinalncapas.dto.request.AuthRequest;
import com.uca.parcialfinalncapas.dto.response.AuthResponse;
import com.uca.parcialfinalncapas.repository.UserRepository;
import com.uca.parcialfinalncapas.security.JwtService;
import com.uca.parcialfinalncapas.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AuthServiceImpl implements AuthService {
    AuthenticationManager authenticationManager;

    PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService, UserRepository userRepository, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        String token = jwtService.generateToken(userDetailsService.loadUserByUsername(authRequest.getUsername()));
        return new AuthResponse(authRequest.getUsername(), token);
    }
}
