package com.example.common.usecase;

import com.example.common.DomainComponent;
import com.example.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@DomainComponent
public class UseCasePublisherImpl implements UseCasePublisher {

    public static final String ERROR_KEY = "domain.usecase.handler.not.detected";

    @Override
    public <R, U extends UseCase> R publish(Class<R> returnClass, U useCase) {
        UseCaseHandler<R, U> useCaseHandler = (UseCaseHandler<R, U>) UseCaseHandlerRegistry.INSTANCE.detectUseCaseHandlerFrom(useCase.getClass());
        validateUseCaseHandlerDetection(useCase, useCaseHandler);
        return useCaseHandler.handler(useCase);
    }

    @Override
    public <U extends UseCase> void publish(U useCase) {
        VoidUseCaseHandler<U> voidUseCaseHandler = (VoidUseCaseHandler<U>) UseCaseHandlerRegistry.INSTANCE.detectVoidUseCaseHandlerFrom(useCase.getClass());
        validateVoidUseCaseHandlerDetection(useCase, voidUseCaseHandler);
    }

    @Override
    public <R> R publish(Class<R> returnClass) {
        NoUseCaseHandler<R> noUseCaseHandler = (NoUseCaseHandler<R>) UseCaseHandlerRegistry.INSTANCE.detectNoUseCaseHandlerFrom(returnClass.getClass());
        validateNoUseCaseHandlerDetection(noUseCaseHandler);
        return noUseCaseHandler.handle();
    }

    private <U extends UseCase, R> void validateUseCaseHandlerDetection(U useCase, UseCaseHandler<R, U> useCaseHandler) {
        if (Objects.isNull(useCaseHandler)){
            log.error("Use case handler cannot be detected for the use case: {}, handlers: {}", useCase, UseCaseHandlerRegistry.INSTANCE.getUseCaseHandlerMap());
            throw new BusinessException(ERROR_KEY);
        }
    }

    private <T extends UseCase> void validateVoidUseCaseHandlerDetection(T useCase, VoidUseCaseHandler<T> voidUseCaseHandler) {
        if (Objects.isNull(voidUseCaseHandler)){
            log.error("Void use case handler cannot be detected for the use case: {}, handlers: {}", useCase, UseCaseHandlerRegistry.INSTANCE.getVoidUseCaseHandlerMap());
            throw new BusinessException(ERROR_KEY);
        }
    }

    private <R> void validateNoUseCaseHandlerDetection(NoUseCaseHandler<R> noUseCaseHandler) {
        if (Objects.isNull(noUseCaseHandler)){
            log.error("Void use case handler cannot be detected for the handlers: {}", UseCaseHandlerRegistry.INSTANCE.getNoUseCaseHandlerMap());
            throw new BusinessException(ERROR_KEY);
        }
    }
}
