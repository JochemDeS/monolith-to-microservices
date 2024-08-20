package com.example.monolithtomicroservices.application.cart;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.application.product.GetProductByIdPort;
import com.example.monolithtomicroservices.domain.CartItem;
import org.springframework.stereotype.Service;

@Service
public class UpdateCartUseCase implements UseCase<UpdateCart, Void> {
    private final GetProductByIdPort getProductByIdPort;
    private final GetCartByUserIdPort getCartByUserIdPort;
    private final UpdateCartPort updateCartPort;

    public UpdateCartUseCase(GetProductByIdPort getProductByIdPort,
                             GetCartByUserIdPort getCartByUserIdPort,
                             UpdateCartPort updateCartPort) {
        this.getProductByIdPort = getProductByIdPort;
        this.getCartByUserIdPort = getCartByUserIdPort;
        this.updateCartPort = updateCartPort;
    }

    @Override
    public Void handle(UpdateCart request) {
        final var product = getProductByIdPort.byId(request.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        final var cart = getCartByUserIdPort.byUserId(request.user().id());

        final var item = CartItem.builder()
                        .product(product)
                        .quantity(request.quantity())
                        .build();

        if (request.quantity() == 0) {
            cart.remove(item);
        } else {
            cart.add(item);
        }

        updateCartPort.update(cart, request.user());
        return null;
    }
}
