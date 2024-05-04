package com.example.monolithtomicroservices.application.common;

public interface UseCase<T, R> {
    R handle (T request);
}
