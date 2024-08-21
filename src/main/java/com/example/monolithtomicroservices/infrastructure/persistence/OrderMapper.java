package com.example.monolithtomicroservices.infrastructure.persistence;

import com.example.monolithtomicroservices.domain.Order;
import com.example.monolithtomicroservices.domain.OrderId;
import com.example.monolithtomicroservices.domain.OrderItem;
import com.example.monolithtomicroservices.domain.User;
import com.example.monolithtomicroservices.infrastructure.persistence.order.OrderEntity;
import com.example.monolithtomicroservices.infrastructure.persistence.order.OrderItemEntity;

import java.util.stream.Collectors;

public class OrderMapper {
    public static Order toDomain(OrderEntity orderEntity) {
        final var items = orderEntity.getItems().stream()
                .map(orderItemEntity -> OrderItem.builder()
                        .orderId(OrderId.builder()
                            .value(orderItemEntity.getOrder().getId())
                            .build())
                        .product(ProductMapper.toDomain(orderItemEntity.getProduct()))
                        .quantity(orderItemEntity.getQuantity())
                        .build())
                .collect(Collectors.toList());


        return Order.builder()
                .id(orderEntity.getId())
                .items(items)
                .build();
    }

    public static OrderEntity toEntity(Order order, User user) {
        final var orderEntity = OrderEntity.builder()
                .id(order.id().value())
                .user(UserMapper.toEntity(user))
                .build();

        final var items = order.items().stream()
                .map(orderItem -> OrderItemEntity.builder()
                        .order(orderEntity)
                        .product(ProductMapper.toEntity(orderItem.product()))
                        .quantity(orderItem.quantity())
                        .build())
                .collect(Collectors.toList());

        orderEntity.setItems(items);

        return orderEntity;
    }
}
