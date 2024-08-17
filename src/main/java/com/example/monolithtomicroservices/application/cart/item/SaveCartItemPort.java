package com.example.monolithtomicroservices.application.cart.item;

import com.example.monolithtomicroservices.domain.CartItem;
import com.example.monolithtomicroservices.domain.CustomerId;

public interface SaveCartItemPort {
    void save(CustomerId customerId, CartItem cartItem);
}
