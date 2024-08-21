package com.example.monolithtomicroservices.application.order;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public class GetAllOrdersForUserUseCase implements UseCase<OrderRequest, Page<Order>> {
    private final GetOrdersByUserIdPort getOrdersByUserIdPort;

    public GetAllOrdersForUserUseCase(GetOrdersByUserIdPort getOrdersByUserIdPort) {
        this.getOrdersByUserIdPort = getOrdersByUserIdPort;
    }

    @Override
    public Page<Order> handle(OrderRequest request) {
        return getOrdersByUserIdPort.byUserId(request.userId(), request.pageable());
    }
}
