package com.example.monolithtomicroservices.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.monolithtomicroservices.application.auth.GetUserPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final GetUserPort getUserPort;

    public JWTRequestFilter(JwtService jwtService, GetUserPort getUserPort) {
        this.jwtService = jwtService;
        this.getUserPort = getUserPort;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);
            try {
                String username = jwtService.getUsernameFromJWT(token);
                getUserPort.byUsername(username).ifPresent(user -> {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
            } catch (JWTDecodeException e) {

            }
        }
        filterChain.doFilter(request, response);
    }
}
