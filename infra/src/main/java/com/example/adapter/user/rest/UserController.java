package com.example.adapter.user.rest;

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

    @GetMapping("/{mail}")
    public ResponseEntity<RetrieveUserResponse> retrieve(@PathVariable String mail) {
        RetrieveUser retrieveUser = publish(RetrieveUser.class, toRetrieveUserUseCase(mail));
        return respond(RetrieveUserResponse.fromModel(retrieveUser));
    }

    private RetrieveUserUseCase toRetrieveUserUseCase(String mail) {
        return new RetrieveUserUseCase(mail);
    }
}
