package com.example.monolithtomicroservices.infrastructure.persistence.cart.item;

import com.example.monolithtomicroservices.application.cart.item.SaveCartItemPort;
import com.example.monolithtomicroservices.application.cart.GetCartByUserIdPort;
import com.example.monolithtomicroservices.domain.*;
import com.example.monolithtomicroservices.infrastructure.persistence.CartMapper;
import com.example.monolithtomicroservices.infrastructure.persistence.cart.CartRepository;
import org.springframework.stereotype.Component;


@Component
public class CartItemSqlPersistenceAdapter implements SaveCartItemPort {

//    public CartItemSqlPersistenceAdapter(CartRepository cartRepository) {
//        this.cartRepository = cartRepository;
//    }

    @Override
    public void save(CustomerId customerId, CartItem cartItem) {

    }
}
