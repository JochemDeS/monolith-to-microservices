package com.example.monolithtomicroservices.infrastructure.persistence.user;

import com.example.monolithtomicroservices.application.auth.GetUserPort;
import com.example.monolithtomicroservices.application.auth.SaveUserPort;
import com.example.monolithtomicroservices.domain.Name;
import com.example.monolithtomicroservices.domain.User;
import com.example.monolithtomicroservices.domain.UserId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserSqlPersistenceAdapter implements SaveUserPort, GetUserPort {
    private final UserRepository userRepository;

    public UserSqlPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        final var userEntity = toEntity(user);
        final var savedUserEntity = userRepository.save(userEntity);
        return toDomain(savedUserEntity);
    }

    @Override
    public Optional<User> byUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username)
                .map(this::toDomain);
    }

    @Override
    public Optional<User> byEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .map(this::toDomain);
    }

    private UserEntity toEntity(User user) {
        return new UserEntity(
                null,
                user.username(),
                user.password(),
                user.email(),
                user.name().firstName(),
                user.name().lastName()
        );
    }

    private User toDomain(UserEntity userEntity) {
        return User.builder()
                .id(UserId.builder()
                        .value(userEntity.id())
                        .build())
                .username(userEntity.username())
                .password(userEntity.password())
                .email(userEntity.email())
                .name(Name.builder()
                        .firstName(userEntity.firstName())
                        .lastName(userEntity.lastName())
                        .build())
                .build();
    }
}
