package com.example.adapter.user.rest.dto;

import com.example.user.usecase.CreateUserUseCase;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(@NotBlank String name, String mail) {

    public CreateUserUseCase toUseCase() {
        return new CreateUserUseCase(name, mail);
    }
}
