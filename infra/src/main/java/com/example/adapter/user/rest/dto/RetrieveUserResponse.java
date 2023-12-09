package com.example.adapter.user.rest.dto;

import com.example.user.model.RetrieveUser;

import java.util.Set;

public record RetrieveUserResponse(String name, String mail, Set<Long> items) {

    public static RetrieveUserResponse fromModel(RetrieveUser model) {
        return new RetrieveUserResponse(model.getName(), model.getMail(), model.getItems());
    }

}
