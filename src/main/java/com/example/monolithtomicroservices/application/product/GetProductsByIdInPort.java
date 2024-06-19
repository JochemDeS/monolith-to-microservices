package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.domain.Product;
import com.example.monolithtomicroservices.domain.ProductId;

import java.util.List;

public interface GetProductsByIdInPort {
    List<Product> byIdIn(List<ProductId> productIds);
}
