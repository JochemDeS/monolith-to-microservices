package com.example.monolithtomicroservices.infrastructure.http.customer;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AddressWriteModel", description = "Model to create an address")
public record AddressWriteModel(
        @Schema(description = "Street name", defaultValue = "Main Street")
        String street,
        @Schema(description = "House number", defaultValue = "1")
        String houseNumber,
        @Schema(description = "Postal code", defaultValue = "1000")
        String zipCode,
        @Schema(description = "City", defaultValue = "Brussels")
        String city,
        @Schema(description = "Country", defaultValue = "Belgium")
        String country
) {

}
