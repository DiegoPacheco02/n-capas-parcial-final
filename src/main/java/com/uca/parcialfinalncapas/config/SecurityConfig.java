package com.uca.parcialfinalncapas.config;

import com.uca.parcialfinalncapas.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtFilter, UserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
<<<<<<< HEAD
                        .requestMatchers("/api/users").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/tickets").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/tickets").hasAuthority("TECH")
                        .requestMatchers(HttpMethod.GET, "/api/tickets/**").hasAnyAuthority("USER", "TECH")
                        .requestMatchers(HttpMethod.PUT, "/api/tickets/**").hasAuthority("TECH")
=======
                        .requestMatchers(HttpMethod.GET, "/api/users").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/users").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/users").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyAuthority("USER")
                        .requestMatchers("/api/users/**").hasAuthority("USER")

                        .requestMatchers(HttpMethod.POST, "/api/tickets").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/tickets").hasAuthority("TECH")
                        .requestMatchers(HttpMethod.GET, "/api/tickets/**").hasAnyAuthority("USER", "TECH")
                        .requestMatchers(HttpMethod.PUT, "/api/tickets").hasAuthority("TECH")
>>>>>>> 7a4138c31fd885ca68894cc1b9202fcd8f3cf466
                        .requestMatchers(HttpMethod.DELETE, "/api/tickets/**").hasAuthority("USER")
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}