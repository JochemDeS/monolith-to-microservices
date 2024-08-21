package com.example.monolithtomicroservices.application.order;

import com.example.monolithtomicroservices.domain.Order;
import com.example.monolithtomicroservices.domain.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GetOrdersByUserIdPort {
    Page<Order> byUserId(UserId userId, Pageable pageable);
}
