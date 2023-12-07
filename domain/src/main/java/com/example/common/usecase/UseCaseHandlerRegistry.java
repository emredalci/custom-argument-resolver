package com.example.common.usecase;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
public class UseCaseHandlerRegistry {

    public static final UseCaseHandlerRegistry INSTANCE = new UseCaseHandlerRegistry();

    private final Map<Class<? extends UseCase>, UseCaseHandler<?, ? extends UseCase>> useCaseHandlerMap;
    private final Map<Class<? extends UseCase>, VoidUseCaseHandler<? extends UseCase>> voidUseCaseHandlerMap;
    private final Map<Class<?>, NoUseCaseHandler<?>> noUseCaseHandlerMap;

    public UseCaseHandlerRegistry() {
        useCaseHandlerMap = new HashMap<>();
        voidUseCaseHandlerMap = new HashMap<>();
        noUseCaseHandlerMap = new HashMap<>();
    }

    public <R, U extends UseCase> void register(Class<U> useCase, UseCaseHandler<R, U> useCaseHandler) {
        log.info("Use case {} is registered by handler {}", useCase.getSimpleName(), useCaseHandler.getClass().getSimpleName());
        useCaseHandlerMap.put(useCase, useCaseHandler);
    }

    public <U extends UseCase> void register(Class<U> useCase, VoidUseCaseHandler<U> useCaseHandler) {
        log.info("Use case {} is registered by void handler {}", useCase.getSimpleName(), useCaseHandler.getClass().getSimpleName());
        voidUseCaseHandlerMap.put(useCase, useCaseHandler);
    }

    public <R> void register(Class<R> returnClass, NoUseCaseHandler<R> useCaseHandler) {
        log.info("Return class {} is registered by no param handler {}", returnClass.getSimpleName(), useCaseHandler.getClass().getSimpleName());
        noUseCaseHandlerMap.put(returnClass, useCaseHandler);
    }

    public UseCaseHandler<?, ? extends UseCase> detectUseCaseHandlerFrom(Class<? extends UseCase> useCaseClass) {
        return useCaseHandlerMap.get(useCaseClass);
    }

    public VoidUseCaseHandler<? extends UseCase> detectVoidUseCaseHandlerFrom(Class<? extends UseCase> useCaseClass) {
        return voidUseCaseHandlerMap.get(useCaseClass);
    }

    public NoUseCaseHandler<?> detectNoUseCaseHandlerFrom(Class<?> returnClass) {
        return noUseCaseHandlerMap.get(returnClass);
    }
}
