package com.example.integration;

import com.example.InfraApplication;
import com.example.TestInfraApplication;
import com.example.adapter.user.jpa.entity.UserEntity;
import com.example.adapter.user.jpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = InfraApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRepositoryIT extends TestInfraApplication {

    @Autowired
    private UserRepository userRepository;

    @Test
    void Should_SaveUser_WhenValid() {
        //GIVEN
        UserEntity userEntity = UserEntity.builder()
                .name("emre")
                .mail("emre@emre.com")
                .build();
        //WHEN
        UserEntity save = userRepository.save(userEntity);
        //THEN
        assertEquals("emre", userEntity.getName());
        assertEquals("emre@emre.com", userEntity.getMail());
    }
}
