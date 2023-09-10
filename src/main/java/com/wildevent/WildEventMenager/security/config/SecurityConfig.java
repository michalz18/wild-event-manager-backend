package com.wildevent.WildEventMenager.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Wyłączenie CSRF
                .authorizeHttpRequests(authRequests -> authRequests
                        .requestMatchers(new AntPathRequestMatcher("/no-auth/**")).permitAll()  // Dostęp publiczny dla URL zaczynających się od "no-auth/"
                        .requestMatchers(new AntPathRequestMatcher("/**"))
                        .hasAnyAuthority("MY-EVENTS", "EVENT-MANAGEMENT", "MAP-CONFIGURATION", "EMPLOYEE-MANAGEMENT")  // Wymagane jedno z uprawnień
                        .anyRequest().authenticated())  // Dla wszystkich innych żądań wymagana jest autentykacja
                .sessionManagement(sessionMgmt -> sessionMgmt  // Konfiguracja zarządzania sesją
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Bezstanowość sesji
                .authenticationProvider(authenticationProvider)  // Dostawca autentykacji
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);  // Dodanie filtra JWT

        return http.build();
    }

}
