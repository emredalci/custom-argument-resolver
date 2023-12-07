package com.example.common.usecase;

public class RegisterHelper {

    public <R, U extends UseCase> void register(Class<U> useCaseClass, UseCaseHandler<R, U> useCaseHandler) {
        UseCaseHandlerRegistry.INSTANCE.register(useCaseClass, useCaseHandler);
    }

    public <U extends UseCase> void register(Class<U> useCaseClass, VoidUseCaseHandler<U> useCaseHandler) {
        UseCaseHandlerRegistry.INSTANCE.register(useCaseClass, useCaseHandler);
    }

    public <R> void register(Class<R> returnClass, NoUseCaseHandler<R> useCaseHandler) {
        UseCaseHandlerRegistry.INSTANCE.register(returnClass, useCaseHandler);
    }
}
