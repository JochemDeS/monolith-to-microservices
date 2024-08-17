package com.example.monolithtomicroservices.infrastructure.persistence.cart;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<CartEntity, Long> {
    CartEntity findByUserId(long userId);
}
