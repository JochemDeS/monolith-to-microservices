package com.example.monolithtomicroservices.application.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.monolithtomicroservices.domain.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    private Algorithm algorithm;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiration}")
    private long expiration;

    private static final String USERNAME_CLAIM = "username";

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateJWT(User user) {
        return JWT.create()
                .withClaim(USERNAME_CLAIM, user.username())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiration)))
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
