package com.example.monolithtomicroservices.application.cart.item;

import com.example.monolithtomicroservices.application.cart.GetCartByUserIdPort;
import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.application.product.GetProductByIdPort;

public class AddCartItemUseCase implements UseCase<AddCartItem, Void> {
    private final GetProductByIdPort getProductByIdPort;
    private final GetCartByUserIdPort getCartByUserIdPort;

    public AddCartItemUseCase(GetProductByIdPort getProductByIdPort, GetCartByUserIdPort getCartByUserIdPort) {
        this.getProductByIdPort = getProductByIdPort;
        this.getCartByUserIdPort = getCartByUserIdPort;
    }

    @Override
    public Void handle(AddCartItem request) {
        final var product = getProductByIdPort.byId(request.productId());
        final var cart = getCartByUserIdPort.byUserId(request.userId());
        return null;
    }
}
