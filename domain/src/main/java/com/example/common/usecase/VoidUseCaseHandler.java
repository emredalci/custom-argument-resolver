package com.example.common.usecase;

public interface VoidUseCaseHandler <U extends UseCase>{

    void handler(U useCase);
}
