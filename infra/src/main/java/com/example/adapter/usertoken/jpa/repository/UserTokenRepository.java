package com.example.adapter.usertoken.jpa.repository;

import com.example.adapter.usertoken.jpa.entity.UserTokenEntity;
import com.example.adapter.usertoken.jpa.entity.UserTokenProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {

    Optional<UserTokenProjection.UserId> findByUuid(String uuid);
}
