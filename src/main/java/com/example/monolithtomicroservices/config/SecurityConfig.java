package com.example.monolithtomicroservices.config;

import com.example.monolithtomicroservices.security.JWTRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JWTRequestFilter jwtRequestFilter;

    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtRequestFilter, AuthorizationFilter.class)
                .authorizeHttpRequests(authorize ->
                    authorize.requestMatchers("/actuator/**", "/auth/login", "/auth/register", "/swagger-ui/*").permitAll()
                            .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());

        return http.build();
    }
}
