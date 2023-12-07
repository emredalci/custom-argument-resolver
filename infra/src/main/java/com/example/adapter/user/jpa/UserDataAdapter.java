package com.example.adapter.user.jpa;

import com.example.adapter.user.jpa.entity.UserEntity;
import com.example.adapter.user.jpa.repository.UserRepository;
import com.example.common.DomainComponent;
import com.example.common.cache.CacheName;
import com.example.common.exception.BusinessException;
import com.example.user.model.CreateUser;
import com.example.user.model.RetrieveUser;
import com.example.user.port.UserPort;
import com.example.user.usecase.CreateUserUseCase;
import com.example.user.usecase.RetrieveUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

@DomainComponent
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
        return new CreateUser(true);
    }

    @Override
    @Cacheable(cacheNames = CacheName.USER,key = "#useCase.mail().toString()",unless = "#result == null", cacheManager = "cache-manager")
    @Transactional(readOnly = true)
    public RetrieveUser get(RetrieveUserUseCase useCase) {
        UserEntity userEntity = userRepository.findByMail(useCase.mail()).orElseThrow(() -> new BusinessException("user.not.found"));
        return new RetrieveUser(userEntity.getName(), useCase.mail());
    }
}
