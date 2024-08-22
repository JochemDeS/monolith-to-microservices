package com.example.monolithtomicroservices.application.order;

import com.example.monolithtomicroservices.application.cart.GetCartByUserIdPort;
import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.domain.Cart;
import com.example.monolithtomicroservices.domain.Order;
import com.example.monolithtomicroservices.domain.OrderItem;
import com.example.monolithtomicroservices.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateOrderUseCase implements UseCase<User, Order>{
    private final SaveOrderPort saveOrderPort;
    private final GetCartByUserIdPort getCartByUserIdPort;

    public CreateOrderUseCase(SaveOrderPort saveOrderPort, GetCartByUserIdPort getCartByUserIdPort) {
        this.saveOrderPort = saveOrderPort;
        this.getCartByUserIdPort = getCartByUserIdPort;
    }

    @Override
    public Order handle(User user) {
        var cart = getCartByUserIdPort.byUserId(user.id());

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        var order = createAndSaveInitialOrder(user);
        var orderItems = createOrderItems(cart, order);

        return saveOrderWithItems(order, user, orderItems);
    }

    private Order createAndSaveInitialOrder(User user) {
        Order order = Order.builder()
                .items(List.of())
                .build();
        return saveOrderPort.save(order, user);
    }

    private List<OrderItem> createOrderItems(Cart cart, Order order) {
        return cart.getItems().stream()
                .map(item -> OrderItem.builder()
                        .orderId(order.id())
                        .product(item.getProduct())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    private Order saveOrderWithItems(Order order, User user, List<OrderItem> items) {
        order = Order.builder()
                .id(order.id().value())
                .items(items)
                .build();

        return saveOrderPort.save(order, user);
    }
}
