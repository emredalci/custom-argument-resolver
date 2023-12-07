package com.example.common.usecase;

public interface UseCasePublisher {

    <R, T extends UseCase> R publish(Class<R> returnClass, T useCase);

    <T extends UseCase> void publish(T useCase);

    <R> R publish(Class<R> returnClass);

}
