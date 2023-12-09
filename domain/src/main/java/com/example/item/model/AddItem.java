package com.example.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItem {

    private Long userId;

    private Long itemId;

    private Boolean isCreated;
}
