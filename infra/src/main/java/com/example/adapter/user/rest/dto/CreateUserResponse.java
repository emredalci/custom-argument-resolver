package com.example.adapter.user.rest.dto;

import com.example.user.model.CreateUser;

public record CreateUserResponse(boolean isCreated) {

    public static CreateUserResponse fromModel(CreateUser model) {
        return new CreateUserResponse(model.getIsCreated());
    }

}
