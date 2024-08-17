package com.example.monolithtomicroservices.application.cart;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.domain.Cart;
import com.example.monolithtomicroservices.domain.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetCartUseCase implements UseCase<UserId, Cart> {
    private final GetCartByUserIdPort getCartByUserIdPort;

    public GetCartUseCase(GetCartByUserIdPort getCartByUserIdPort) {
        this.getCartByUserIdPort = getCartByUserIdPort;
    }

    @Override
    public Cart handle(UserId request) {
        return getCartByUserIdPort.byUserId(request);
    }
}
