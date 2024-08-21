package com.example.monolithtomicroservices.infrastructure.http.order;

import com.example.monolithtomicroservices.application.order.GetAllOrdersForUserUseCase;
import com.example.monolithtomicroservices.application.order.OrderRequest;
import com.example.monolithtomicroservices.domain.User;
import com.example.monolithtomicroservices.infrastructure.http.auth.DetailedUserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final GetAllOrdersForUserUseCase getAllOrdersForUserUseCase;

    public OrderController(GetAllOrdersForUserUseCase getAllOrdersForUserUseCase) {
        this.getAllOrdersForUserUseCase = getAllOrdersForUserUseCase;
    }

    @GetMapping
    public OrderResponseModel getOrders(@AuthenticationPrincipal User user, Pageable pageable) {
        final var orderRequest = OrderRequest.builder()
                .userId(user.id())
                .pageable(pageable)
                .build();

        final var orders = getAllOrdersForUserUseCase.handle(orderRequest);

        return OrderResponseModel.builder()
                .user(DetailedUserModel.builder()
                        .username(user.username())
                        .email(user.email())
                        .firstName(user.name().firstName())
                        .lastName(user.name().lastName())
                        .build())
                .orders(orders.getContent().stream()
                        .map(order -> OrderReadModel.builder()
                                .id(order.id().value())
                                .totalAmount(order.getTotalAmount())
                                .build())
                        .toList())
                .build();
    }
}
