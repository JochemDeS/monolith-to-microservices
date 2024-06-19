package com.example.monolithtomicroservices.application.auth;

import com.example.monolithtomicroservices.domain.User;

public interface SaveUserPort {
    User save(User user);
}
