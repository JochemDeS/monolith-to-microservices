package com.example.monolithtomicroservices.infrastructure.persistence;

import com.example.monolithtomicroservices.domain.Cart;
import com.example.monolithtomicroservices.domain.CartItem;
import com.example.monolithtomicroservices.domain.CartItemId;
import com.example.monolithtomicroservices.infrastructure.persistence.cart.CartEntity;


public class CartMapper {
    public static Cart toDomain(CartEntity cartEntity) {
        final var items = cartEntity.getItems().stream()
                .map(item -> CartItem.builder()
                        .id(CartItemId.builder()
                                .value(item.getId())
                                .build())
                        .product(ProductMapper.mapToProduct(item.getProduct()))
                        .quantity(item.getQuantity())
                        .build()
                )
                .toList();

        return Cart.builder()
                .items(items)
                .build();
    }
}
