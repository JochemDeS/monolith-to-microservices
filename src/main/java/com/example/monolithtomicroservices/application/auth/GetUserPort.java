package com.example.monolithtomicroservices.application.auth;

import com.example.monolithtomicroservices.domain.User;

import java.util.Optional;

public interface GetUserPort {
    Optional<User> byUsername(String username);
    Optional<User> byEmail(String email);
}
