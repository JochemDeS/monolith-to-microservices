package com.example.monolithtomicroservices.infrastructure.http.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginUserWriteModel(
        @NotBlank
        String username,
        @NotBlank
        String password) {
}
