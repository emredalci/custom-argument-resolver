package com.example.user.port;

import com.example.user.model.CreateUser;
import com.example.user.model.RetrieveUser;
import com.example.user.usecase.CreateUserUseCase;
import com.example.user.usecase.RetrieveUserUseCase;

public interface UserPort {

    CreateUser save(CreateUserUseCase useCase);

    RetrieveUser get(RetrieveUserUseCase useCase);

}
