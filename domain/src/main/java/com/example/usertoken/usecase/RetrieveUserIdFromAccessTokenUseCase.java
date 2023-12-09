package com.example.usertoken.usecase;

import com.example.common.usecase.UseCase;

public record RetrieveUserIdFromAccessTokenUseCase(String accessToken) implements UseCase {
}
