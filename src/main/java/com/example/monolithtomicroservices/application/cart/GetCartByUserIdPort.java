package com.example.monolithtomicroservices.application.cart;

import com.example.monolithtomicroservices.domain.Cart;
import com.example.monolithtomicroservices.domain.UserId;

public interface GetCartByUserIdPort {
    Cart byUserId(UserId userId);
}
