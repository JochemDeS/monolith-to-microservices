package com.example.monolithtomicroservices.infrastructure.http.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "CustomerWriteModel", description = "Model to create a customer")
public record CustomerWriteModel(
        @NotBlank @Size(min = 2, max = 30) @Schema(description = "First name", defaultValue = "John")
        String firstName,
        @NotBlank @Size(min = 2, max = 30) @Schema(description = "Last name", defaultValue = "Doe")
        String lastName,
        @NotNull @Email @Schema(description = "Email address", defaultValue = "john.doe@example.com")
        String email,
        @NotNull @Schema(description = "Address of the customer")
        AddressWriteModel address
) {
}
