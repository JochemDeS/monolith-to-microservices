package com.example.monolithtomicroservices.infrastructure.http.cart;

import com.example.monolithtomicroservices.application.cart.GetCartUseCase;
import com.example.monolithtomicroservices.application.cart.ResetCartUseCase;
import com.example.monolithtomicroservices.application.cart.UpdateCart;
import com.example.monolithtomicroservices.application.cart.UpdateCartUseCase;
import com.example.monolithtomicroservices.domain.ProductId;
import com.example.monolithtomicroservices.domain.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@Tag(name = "Cart", description = "All endpoints for the cart")
public class CartController {
    private final GetCartUseCase getCartUseCase;
    private final UpdateCartUseCase updateCartUseCase;
    private final ResetCartUseCase resetCartUseCase;

    public CartController(GetCartUseCase getCartUseCase, UpdateCartUseCase updateCartUseCase, ResetCartUseCase resetCartUseCase) {
        this.getCartUseCase = getCartUseCase;
        this.updateCartUseCase = updateCartUseCase;
        this.resetCartUseCase = resetCartUseCase;
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

    @PostMapping("/{productId}")
    public void update(@AuthenticationPrincipal User user,
                       @PathVariable @NotNull @Positive int productId,
                       @Valid @RequestBody UpdateCartModel request) {
        final var addCartItem = UpdateCart.builder()
                .user(user)
                .productId(ProductId.builder()
                        .value(productId)
                        .build())
                .quantity(request.quantity())
                .build();

        updateCartUseCase.handle(addCartItem);
    }

    @PostMapping("/reset")
    public void reset(@AuthenticationPrincipal User user) {
        resetCartUseCase.handle(user);
    }
}
