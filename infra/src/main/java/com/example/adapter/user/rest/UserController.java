package com.example.adapter.user.rest;

import com.example.adapter.user.resolver.RequestUserIdFromAccessToken;
import com.example.adapter.user.rest.dto.CreateUserRequest;
import com.example.adapter.user.rest.dto.CreateUserResponse;
import com.example.adapter.user.rest.dto.RetrieveUserResponse;
import com.example.common.rest.BaseController;
import com.example.user.model.CreateUser;
import com.example.user.model.RetrieveUser;
import com.example.user.usecase.RetrieveUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends BaseController {

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> create(@RequestBody @Valid CreateUserRequest createUserRequest) {
        CreateUser createUser = publish(CreateUser.class, createUserRequest.toUseCase());
        return respond(CreateUserResponse.fromModel(createUser));
    }

    @GetMapping()
    public ResponseEntity<RetrieveUserResponse> retrieve(@RequestUserIdFromAccessToken Long userId) {
        RetrieveUser retrieveUser = publish(RetrieveUser.class, toRetrieveUserUseCase(userId));
        return respond(RetrieveUserResponse.fromModel(retrieveUser));
    }

    private RetrieveUserUseCase toRetrieveUserUseCase(Long userId) {
        return new RetrieveUserUseCase(userId);
    }
}
