package com.example.user.usecase;

import com.example.common.DomainComponent;
import com.example.common.usecase.RegisterHelper;
import com.example.common.usecase.UseCaseHandler;
import com.example.user.model.RetrieveUser;
import com.example.user.port.UserPort;

@DomainComponent
public class RetrieveUserHandler extends RegisterHelper implements UseCaseHandler<RetrieveUser, RetrieveUserUseCase> {

    private final UserPort userPort;

    public RetrieveUserHandler(UserPort userPort) {
        this.userPort = userPort;
        register(RetrieveUserUseCase.class, this);
    }

    @Override
    public RetrieveUser handler(RetrieveUserUseCase useCase) {
        return userPort.get(useCase);
    }
}
