package com.example.item.usecase;

import com.example.common.usecase.UseCase;


public record AddItemUseCase(Long userId, Long itemId) implements UseCase {
}
