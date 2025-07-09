package com.uca.parcialfinalncapas.service.impl;

import com.uca.parcialfinalncapas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.uca.parcialfinalncapas.entities.User user = userRepository.findByCorreo(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        User.UserBuilder builder = User.withUsername(user.getCorreo());
        builder.username(user.getCorreo());
        builder.password(user.getPassword());
        builder.authorities(user.getNombreRol());
        return builder.build();
    }
}