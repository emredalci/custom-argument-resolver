package com.example.user.usecase;

import com.example.common.DomainComponent;
import com.example.common.usecase.RegisterHelper;
import com.example.common.usecase.UseCaseHandler;
import com.example.user.model.CreateUser;
import com.example.user.port.UserPort;

@DomainComponent
public class CreateUserHandler extends RegisterHelper implements UseCaseHandler<CreateUser, CreateUserUseCase> {

    private final UserPort userPort;

    public CreateUserHandler(UserPort userPort) {
        this.userPort = userPort;
        register(CreateUserUseCase.class, this);
    }

    @Override
    public CreateUser handler(CreateUserUseCase useCase) {
        return userPort.save(useCase);
    }
}
