package com.example.usertoken.usecase;

import com.example.common.DomainComponent;
import com.example.common.usecase.RegisterHelper;
import com.example.common.usecase.UseCaseHandler;
import com.example.usertoken.model.RetrieveUserId;
import com.example.usertoken.port.UserTokenPort;

@DomainComponent
public class RetrieveUserIdFromAccessTokenHandler extends RegisterHelper implements UseCaseHandler<RetrieveUserId, RetrieveUserIdFromAccessTokenUseCase> {

    private final UserTokenPort userTokenPort;

    public RetrieveUserIdFromAccessTokenHandler(UserTokenPort userTokenPort) {
        this.userTokenPort = userTokenPort;
        register(RetrieveUserIdFromAccessTokenUseCase.class, this);
    }

    @Override
    public RetrieveUserId handler(RetrieveUserIdFromAccessTokenUseCase useCase) {
        Long userId = userTokenPort.getUserIdByAccessToken(useCase.accessToken());
        return new RetrieveUserId(userId);
    }
}
