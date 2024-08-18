package com.example.monolithtomicroservices.application.cart;

import com.example.monolithtomicroservices.domain.Cart;
import com.example.monolithtomicroservices.domain.User;

public interface UpdateCartPort {
    void update(Cart cart, User user);
}
