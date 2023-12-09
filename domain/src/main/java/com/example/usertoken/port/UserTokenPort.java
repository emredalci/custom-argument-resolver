package com.example.usertoken.port;

public interface UserTokenPort {

    String save(Long userId);

    Long getUserIdByAccessToken(String accessToken);
}
