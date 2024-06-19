package com.example.monolithtomicroservices.infrastructure.persistence.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "User")
@Table(name = "users")
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) @NotNull
    private String username;
    @Column @NotNull
    private String password;
    @Column(unique = true) @NotNull
    private String email;
    @Column @NotNull
    private String firstName;
    @Column @NotNull
    private String lastName;

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, String email, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long id() {
        return id;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String email() {
        return email;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }
}
