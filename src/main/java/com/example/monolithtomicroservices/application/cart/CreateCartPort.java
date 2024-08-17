package com.example.monolithtomicroservices.application.cart;

import com.example.monolithtomicroservices.domain.User;

public interface CreateCartPort {
    void create(User user);
}
