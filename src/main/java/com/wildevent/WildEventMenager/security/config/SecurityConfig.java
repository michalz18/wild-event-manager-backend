package com.wildevent.WildEventMenager.security.config;

import com.wildevent.WildEventMenager.security.AccessUrlProvider;
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
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
                .authorizeHttpRequests(authRequests -> authRequests
                        .requestMatchers(new AntPathRequestMatcher("/no-auth/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher(AccessUrlProvider.STAFF_MANAGEMENT + "/**")).hasAuthority("EMPLOYEE-MANAGEMENT")
                        .requestMatchers(new AntPathRequestMatcher(AccessUrlProvider.MAP_CONFIG + "/**")).hasAuthority("MAP-CONFIGURATION")
                        .requestMatchers(new AntPathRequestMatcher(AccessUrlProvider.MY_EVENTS + "/**")).hasAuthority("MY-EVENTS")
                        .requestMatchers(new AntPathRequestMatcher(AccessUrlProvider.EVENT_MANAGEMENT + "/**")).hasAuthority("EVENT-MANAGEMENT")
                        .anyRequest().authenticated())
                .sessionManagement(sessionMgmt -> sessionMgmt
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}