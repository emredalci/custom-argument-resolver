package com.example.user.usecase;

import com.example.common.usecase.UseCase;

public record CreateUserUseCase(String name, String mail) implements UseCase {
}
