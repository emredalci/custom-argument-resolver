package com.example.common.usecase;

public interface UseCaseHandler <R,U extends UseCase>{

    R handler(U useCase);
}
