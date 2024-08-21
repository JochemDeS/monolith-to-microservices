package com.example.monolithtomicroservices.application.order;

import com.example.monolithtomicroservices.domain.Order;
import com.example.monolithtomicroservices.domain.OrderId;

import java.util.Optional;

public interface GetOrderByIdPort {
    Optional<Order> getOrderById(OrderId orderId);
}
