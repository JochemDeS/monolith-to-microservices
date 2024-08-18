package com.example.monolithtomicroservices.infrastructure.persistence.cart;

import com.example.monolithtomicroservices.application.cart.CreateCartPort;
import com.example.monolithtomicroservices.application.cart.GetCartByUserIdPort;
import com.example.monolithtomicroservices.application.cart.UpdateCartPort;
import com.example.monolithtomicroservices.domain.Cart;
import com.example.monolithtomicroservices.domain.User;
import com.example.monolithtomicroservices.domain.UserId;
import com.example.monolithtomicroservices.infrastructure.persistence.CartMapper;
import com.example.monolithtomicroservices.infrastructure.persistence.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class CartSqlPersistenceAdapter implements CreateCartPort, GetCartByUserIdPort, UpdateCartPort {
    private final CartRepository cartRepository;

    public CartSqlPersistenceAdapter(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void create(User user) {
        final var userEntity = UserMapper.toEntity(user);
        final var cart = CartEntity.builder()
                .user(userEntity)
                .build();
        cartRepository.save(cart);
    }

    @Override
    public Cart byUserId(UserId userId) {
        return CartMapper.toDomain(cartRepository.findByUserId(userId.value()));
    }

    @Override
    public void update(Cart cart, User user) {
        final var cartEntity = CartMapper.toEntity(cart, user);
        cartRepository.save(cartEntity);
    }
}
