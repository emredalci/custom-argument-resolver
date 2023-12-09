package com.example.item.usecase;

import com.example.common.DomainComponent;
import com.example.common.usecase.RegisterHelper;
import com.example.common.usecase.UseCaseHandler;
import com.example.item.model.AddItem;
import com.example.item.port.ItemPort;

@DomainComponent
public class AddItemHandler extends RegisterHelper implements UseCaseHandler<AddItem, AddItemUseCase> {
    private final ItemPort itemPort;

    public AddItemHandler(ItemPort itemPort) {
        this.itemPort = itemPort;
        register(AddItemUseCase.class, this);
    }

    @Override
    public AddItem handler(AddItemUseCase useCase) {
        boolean isAdded = itemPort.add(useCase.userId(), useCase.itemId());
        return new AddItem(useCase.userId(), useCase.itemId(), isAdded);
    }
}
