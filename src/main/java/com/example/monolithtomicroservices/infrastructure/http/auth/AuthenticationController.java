package com.example.monolithtomicroservices.infrastructure.http.auth;

import com.example.monolithtomicroservices.application.auth.RegisterUserRequest;
import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.domain.Name;
import com.example.monolithtomicroservices.domain.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UseCase<RegisterUserRequest, User> registerUserUseCase;

    public AuthenticationController(UseCase<RegisterUserRequest, User> registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterUserWriteModel request) {
        final var registerUser = RegisterUserRequest.builder()
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .name(Name.builder()
                        .firstName(request.firstName())
                        .lastName(request.lastName())
                        .build())
                .build();

        registerUserUseCase.handle(registerUser);
    }
}
