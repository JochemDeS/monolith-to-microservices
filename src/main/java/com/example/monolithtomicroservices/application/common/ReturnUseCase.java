package com.example.monolithtomicroservices.application.common;

public interface ReturnUseCase<T> {
    T handle();
}
