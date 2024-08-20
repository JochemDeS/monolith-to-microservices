package com.example.monolithtomicroservices.application.cart;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.domain.User;
import org.springframework.stereotype.Service;

@Service
public class ResetCartUseCase implements UseCase<User, Void> {
    private final GetCartByUserIdPort getCartByUserIdPort;
    private final UpdateCartPort updateCartPort;

    public ResetCartUseCase(GetCartByUserIdPort getCartByUserIdPort, UpdateCartPort updateCartPort) {
        this.getCartByUserIdPort = getCartByUserIdPort;
        this.updateCartPort = updateCartPort;
    }

    @Override
    public Void handle(User request) {
        final var cart = getCartByUserIdPort.byUserId(request.id());

        cart.reset();
        updateCartPort.update(cart, request);
        return null;
    }
}
