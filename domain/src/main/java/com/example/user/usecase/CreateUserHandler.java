package com.example.user.usecase;

import com.example.common.DomainComponent;
import com.example.common.usecase.RegisterHelper;
import com.example.common.usecase.UseCaseHandler;
import com.example.user.model.CreateUser;
import com.example.user.port.UserPort;
import com.example.usertoken.port.UserTokenPort;

@DomainComponent
public class CreateUserHandler extends RegisterHelper implements UseCaseHandler<CreateUser, CreateUserUseCase> {

    private final UserPort userPort;
    private final UserTokenPort userTokenPort;

    public CreateUserHandler(UserPort userPort, UserTokenPort userTokenPort) {
        this.userPort = userPort;
        this.userTokenPort = userTokenPort;
        register(CreateUserUseCase.class, this);
    }

    @Override
    public CreateUser handler(CreateUserUseCase useCase) {
        CreateUser createdUser = userPort.save(useCase);
        if (Boolean.TRUE.equals(createdUser.getIsCreated())) {
            String accessToken = userTokenPort.save(createdUser.getUserId());
            createdUser.setAccessToken(accessToken);
        }
        return createdUser;
    }
}
