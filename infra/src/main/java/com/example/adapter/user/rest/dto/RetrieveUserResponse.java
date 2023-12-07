package com.example.adapter.user.rest.dto;

import com.example.user.model.RetrieveUser;

public record RetrieveUserResponse(String name, String mail) {

    public static RetrieveUserResponse fromModel(RetrieveUser model) {
        return new RetrieveUserResponse(model.getName(), model.getMail());
    }

}
