package com.example.monolithtomicroservices.infrastructure.http.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "AddressWriteModel", description = "Model to create an address")
public record AddressWriteModel(
        @NotBlank @Size(min = 4, max = 30) @Schema(description = "Street name", defaultValue = "Main Street")
        String street,
        @NotBlank @Size(max = 30) @Schema(description = "House number", defaultValue = "1")
        String houseNumber,
        @NotBlank @Size(min = 2, max = 10) @Schema(description = "Postal code", defaultValue = "1000")
        String zipCode,
        @NotBlank @Size(min = 3, max = 30) @Schema(description = "City", defaultValue = "Brussels")
        String city,
        @NotBlank @Size(min = 3, max = 30) @Schema(description = "Country", defaultValue = "Belgium")
        String country
) {

}
