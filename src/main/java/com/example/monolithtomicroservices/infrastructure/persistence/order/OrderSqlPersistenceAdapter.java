package com.example.monolithtomicroservices.infrastructure.persistence.order;

import com.example.monolithtomicroservices.application.order.GetOrderByIdPort;
import com.example.monolithtomicroservices.application.order.GetOrdersByUserIdPort;
import com.example.monolithtomicroservices.domain.Order;
import com.example.monolithtomicroservices.domain.OrderId;
import com.example.monolithtomicroservices.domain.UserId;
import com.example.monolithtomicroservices.infrastructure.persistence.OrderMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class OrderSqlPersistenceAdapter implements GetOrdersByUserIdPort, GetOrderByIdPort {
    private final OrderRepository orderRepository;

    public OrderSqlPersistenceAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Page<Order> byUserId(UserId userId, Pageable pageable) {
        final var orders = orderRepository.findAllByUserId(userId.value(), pageable);
        final var orderList = orders.stream()
                .map(OrderMapper::toDomain)
                .toList();

        return new PageImpl<>(orderList, pageable, orders.getTotalElements());
    }

    @Override
    public Optional<Order> getOrderById(OrderId orderId) {
        return orderRepository.findById(orderId.value())
                .map(OrderMapper::toDomain);
    }
}
