package com.example.monolithtomicroservices.infrastructure.persistence;

import com.example.monolithtomicroservices.domain.*;
import com.example.monolithtomicroservices.infrastructure.persistence.cart.CartEntity;
import com.example.monolithtomicroservices.infrastructure.persistence.cart.CartItemEntity;

import java.util.stream.Collectors;


public class CartMapper {
    public static Cart toDomain(CartEntity cartEntity) {
        final var items = cartEntity.getItems().stream()
                .map(item -> CartItem.builder()
                        .id(CartItemId.builder()
                                .value(item.getId())
                                .build())
                        .product(ProductMapper.toDomain(item.getProduct()))
                        .quantity(item.getQuantity())
                        .build()
                )
                .collect(Collectors.toList());

        return Cart.builder()
                .id(CartId.builder()
                        .value(cartEntity.getId())
                        .build())
                .items(items)
                .build();
    }

    public static CartEntity toEntity(Cart cart, User user) {
        final var cartEntity = CartEntity.builder()
                .id(cart.getId().value())
                .user(UserMapper.toEntity(user))
                .build();

        final var items = cart.getItems().stream()
                .map(item -> CartItemEntity.builder()
                        .cart(cartEntity)
                        .product(ProductMapper.toEntity(item.getProduct()))
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());

        cartEntity.setItems(items);

        return cartEntity;
    }
}
