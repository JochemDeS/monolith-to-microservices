package com.example.monolithtomicroservices.infrastructure.http.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

@Schema(name = "CustomerCartWriteModel", description = "Model to add products to the cart of a customer")
public record CustomerCartWriteModel(
        @NotNull @Schema(description = "A map where the key is the product ID and the value is the quantity",
                defaultValue = "{ \"89867\": 2, \"2\": 1 }")
        Map<String, Integer> products) {

}
