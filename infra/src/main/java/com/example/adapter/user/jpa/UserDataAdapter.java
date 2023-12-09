package com.example.adapter.user.jpa;

import com.example.adapter.item.jpa.entity.ItemEntity;
import com.example.adapter.user.jpa.entity.UserEntity;
import com.example.adapter.user.jpa.repository.UserRepository;
import com.example.common.exception.BusinessException;
import com.example.user.model.CreateUser;
import com.example.user.model.RetrieveUser;
import com.example.user.port.UserPort;
import com.example.user.usecase.CreateUserUseCase;
import com.example.user.usecase.RetrieveUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDataAdapter implements UserPort {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public CreateUser save(CreateUserUseCase useCase) {
        UserEntity userEntity = UserEntity.builder()
                .name(useCase.name())
                .mail(useCase.mail())
                .build();
        userRepository.save(userEntity);
        return CreateUser.builder().userId(userEntity.getId()).isCreated(true).build();
    }

    @Override
    public RetrieveUser retrieve(RetrieveUserUseCase useCase) {
        return userRepository.findById(useCase.userId())
                .map(this::getUserEntityRetrieveUserFunction)
                .orElseThrow(() -> new BusinessException("user.not.found"));
    }

    private RetrieveUser getUserEntityRetrieveUserFunction(UserEntity userEntity) {
        Set<Long> items = userEntity.getItems().stream().map(ItemEntity::getId).collect(Collectors.toUnmodifiableSet());
        return new RetrieveUser(userEntity.getName(), userEntity.getMail(), items);
    }
}
