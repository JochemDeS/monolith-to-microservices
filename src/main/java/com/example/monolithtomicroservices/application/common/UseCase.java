package com.example.monolithtomicroservices.application.common;

public interface UseCase<T, R> {
    T handle (R request);
}
