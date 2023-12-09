package com.example.adapter.usertoken.jpa;

import com.example.adapter.usertoken.jpa.entity.UserTokenEntity;
import com.example.adapter.usertoken.jpa.repository.UserTokenRepository;
import com.example.common.exception.BusinessException;
import com.example.usertoken.port.UserTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserTokenDataAdapter implements UserTokenPort {

    private final UserTokenRepository userTokenRepository;

    @Override
    public String save(Long userId) {
        UserTokenEntity userTokenEntity = UserTokenEntity.builder()
                .uuid(UUID.randomUUID().toString())
                .userId(userId)
                .build();
        userTokenRepository.save(userTokenEntity);
        return userTokenEntity.getUuid();
    }

    @Override
    public Long getUserIdByAccessToken(String accessToken) {
        return userTokenRepository.findByUuid(accessToken)
                .orElseThrow(() -> new BusinessException("user.not.found"))
                .getUserId();
    }
}
