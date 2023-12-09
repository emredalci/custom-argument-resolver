package com.example.adapter.item.rest;

import com.example.adapter.item.rest.dto.AddItemResponse;
import com.example.adapter.user.resolver.RequestUserIdFromAccessToken;
import com.example.common.rest.BaseController;
import com.example.item.model.AddItem;
import com.example.item.usecase.AddItemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController extends BaseController {

    @PostMapping("/{itemId}")
    public ResponseEntity<AddItemResponse> add(@RequestUserIdFromAccessToken Long userId, @PathVariable Long itemId) {
        AddItem addItem = publish(AddItem.class, new AddItemUseCase(userId, itemId));
        return respond(AddItemResponse.fromModel(addItem));
    }
}
