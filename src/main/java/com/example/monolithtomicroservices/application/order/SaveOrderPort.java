package com.example.monolithtomicroservices.application.order;

import com.example.monolithtomicroservices.domain.Order;
import com.example.monolithtomicroservices.domain.User;

public interface SaveOrderPort {
    Order save(Order order, User user);
}
