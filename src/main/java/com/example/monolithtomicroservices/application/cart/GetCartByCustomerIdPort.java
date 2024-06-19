package com.example.monolithtomicroservices.application.cart;

import com.example.monolithtomicroservices.domain.Cart;
import com.example.monolithtomicroservices.domain.CustomerId;

public interface GetCartByCustomerIdPort {
    Cart getCartByCustomerId(CustomerId customerId);
}
