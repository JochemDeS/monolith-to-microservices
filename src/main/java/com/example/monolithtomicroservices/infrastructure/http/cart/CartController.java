package com.example.monolithtomicroservices.infrastructure.http.cart;

import com.example.monolithtomicroservices.application.cart.GetCartUseCase;
import com.example.monolithtomicroservices.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final GetCartUseCase getCartUseCase;

    public CartController(GetCartUseCase getCartUseCase) {
        this.getCartUseCase = getCartUseCase;
    }

    @GetMapping
    public CartReadModel getCart(@AuthenticationPrincipal User user) {
        final var cart = getCartUseCase.handle(user.id());

        return CartReadModel.builder()
                .items(cart.items().stream()
                        .map(item -> CartItemReadModel.builder()
                                .name(item.getProduct().title())
                                .brand(item.getProduct().brand().name())
                                .category(item.getProduct().category().name())
                                .thumbnail(item.getProduct().thumbnail())
                                .price(item.getProduct().price())
                                .quantity(item.getQuantity())
                                .build())
                        .toList())
                .totalPrice(cart.getTotalPrice())
                .build();
    }
}
