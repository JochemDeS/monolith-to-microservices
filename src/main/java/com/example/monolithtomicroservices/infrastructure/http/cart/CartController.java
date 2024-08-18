package com.example.monolithtomicroservices.infrastructure.http.cart;

import com.example.monolithtomicroservices.application.cart.GetCartUseCase;
import com.example.monolithtomicroservices.application.cart.UpdateCart;
import com.example.monolithtomicroservices.application.cart.UpdateCartUseCase;
import com.example.monolithtomicroservices.domain.ProductId;
import com.example.monolithtomicroservices.domain.User;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final GetCartUseCase getCartUseCase;
    private final UpdateCartUseCase updateCartUseCase;

    public CartController(GetCartUseCase getCartUseCase, UpdateCartUseCase updateCartUseCase) {
        this.getCartUseCase = getCartUseCase;
        this.updateCartUseCase = updateCartUseCase;
    }

    @GetMapping
    public CartReadModel getCart(@AuthenticationPrincipal User user) {
        final var cart = getCartUseCase.handle(user.id());

        return CartReadModel.builder()
                .items(cart.getItems().stream()
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

    @PostMapping
    public void update(@AuthenticationPrincipal User user, @Valid @RequestBody UpdateCartModel request) {
        final var addCartItem = UpdateCart.builder()
                .user(user)
                .productId(ProductId.builder()
                        .value(request.productId())
                        .build())
                .quantity(request.quantity())
                .build();

        updateCartUseCase.handle(addCartItem);
    }
}
