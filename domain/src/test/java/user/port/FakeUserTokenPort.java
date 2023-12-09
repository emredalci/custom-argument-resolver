package user.port;

import com.example.usertoken.port.UserTokenPort;

import java.util.UUID;

public class FakeUserTokenPort implements UserTokenPort {

    @Override
    public String save(Long userId) {
        return UUID.randomUUID().toString();
    }

    @Override
    public Long getUserIdByAccessToken(String accessToken) {
        return 1L;
    }
}
