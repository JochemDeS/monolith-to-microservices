package com.example.monolithtomicroservices.infrastructure.http.order;

import com.example.monolithtomicroservices.application.order.GetAllOrdersForUserUseCase;
import com.example.monolithtomicroservices.application.order.GetOrderByIdUseCase;
import com.example.monolithtomicroservices.application.order.OrderRequest;
import com.example.monolithtomicroservices.domain.OrderId;
import com.example.monolithtomicroservices.domain.User;
import com.example.monolithtomicroservices.infrastructure.http.auth.DetailedUserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final GetAllOrdersForUserUseCase getAllOrdersForUserUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;

    public OrderController(GetAllOrdersForUserUseCase getAllOrdersForUserUseCase, GetOrderByIdUseCase getOrderByIdUseCase) {
        this.getAllOrdersForUserUseCase = getAllOrdersForUserUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
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

    @GetMapping("/{id}")
    public OrderReadModel getOrder(@PathVariable String id) {
        final var orderId = OrderId.builder()
                .value(Long.parseLong(id))
                .build();

        final var order = getOrderByIdUseCase.handle(orderId);

        return OrderReadModel.builder()
                .id(order.id().value())
                .totalAmount(order.getTotalAmount())
                .build();
    }
}
