package com.example.monolithtomicroservices.infrastructure.http.auth;

import jakarta.validation.constraints.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record RegisterUserWriteModel(
        @NotBlank @Size(min = 4, max = 20) @DefaultValue("johndoe") String username,
        @NotBlank @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$\"") String password,
        @NotNull @Email @DefaultValue("john.doe@test.com") String email,
        @NotBlank @Size(min = 2, max = 30) @DefaultValue("John") String firstName,
        @NotBlank @Size(min = 2, max = 30) @DefaultValue("Doe") String lastName
) {
}
