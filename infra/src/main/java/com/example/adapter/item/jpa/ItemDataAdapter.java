package com.example.adapter.item.jpa;

import com.example.adapter.item.jpa.entity.ItemEntity;
import com.example.adapter.user.jpa.entity.UserEntity;
import com.example.adapter.user.jpa.repository.UserRepository;
import com.example.common.exception.BusinessException;
import com.example.item.port.ItemPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemDataAdapter implements ItemPort {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public boolean add(Long userId, Long itemId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new BusinessException("user.not.found"));
        ItemEntity itemEntity = ItemEntity.builder().build();
        userEntity.addItem(itemEntity);
        return true;
    }
}
