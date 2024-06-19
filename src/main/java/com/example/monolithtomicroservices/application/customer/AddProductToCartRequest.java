package com.example.monolithtomicroservices.application.customer;

import com.example.monolithtomicroservices.domain.CustomerId;
import com.example.monolithtomicroservices.domain.ProductId;

import java.util.Map;

public record AddProductToCartRequest(CustomerId customer, Map<ProductId, Integer> products) {
}
