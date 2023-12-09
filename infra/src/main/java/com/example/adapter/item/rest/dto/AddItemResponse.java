package com.example.adapter.item.rest.dto;

import com.example.item.model.AddItem;

public record AddItemResponse(boolean isCreated) {

    public static AddItemResponse fromModel(AddItem model) {
        return new AddItemResponse(model.getIsCreated());
    }
}
