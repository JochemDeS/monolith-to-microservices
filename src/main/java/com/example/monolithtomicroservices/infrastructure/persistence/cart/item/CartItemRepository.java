package com.example.monolithtomicroservices.infrastructure.persistence.cart.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    List<CartItemEntity> findByCartId(long cartId);
}
