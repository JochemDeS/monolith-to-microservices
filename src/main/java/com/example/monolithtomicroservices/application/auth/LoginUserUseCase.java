package com.example.monolithtomicroservices.application.auth;

import com.example.monolithtomicroservices.application.common.UseCase;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserUseCase implements UseCase<LoginUserRequest, String> {
    private final GetUserPort getUserPort;
    private final EncryptionService encryptionService;
    private final JwtService jwtService;

    public LoginUserUseCase(GetUserPort getUserPort, EncryptionService encryptionService, JwtService jwtService) {
        this.getUserPort = getUserPort;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    @Override
    public String handle(LoginUserRequest request) {
        final var user = getUserPort.byUsername(request.username())
                .orElseThrow(() -> new AccessDeniedException("User not found"));

        if (!encryptionService.verify(request.password(), user.password()))
            throw new AccessDeniedException("Invalid password");

        return jwtService.generateJWT(user);
    }
}
