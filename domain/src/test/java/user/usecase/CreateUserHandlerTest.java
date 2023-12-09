package user.usecase;

import com.example.user.model.CreateUser;
import com.example.user.port.UserPort;
import com.example.usertoken.port.UserTokenPort;
import com.example.user.usecase.CreateUserHandler;
import com.example.user.usecase.CreateUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.port.FakeUserPort;
import user.port.FakeUserTokenPort;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserHandlerTest {

    private CreateUserHandler createUserHandler;

    @BeforeEach
    void setUp() {
        UserPort userPort = new FakeUserPort();
        UserTokenPort userTokenPort = new FakeUserTokenPort();
        createUserHandler = new CreateUserHandler(userPort, userTokenPort);
    }

    @Test
    void Should_ReturnIsCreatedTrue_When_ValidUser() {
        //GIVEN
        CreateUserUseCase useCase = new CreateUserUseCase("emre", "emre@emre.com");
        //WHEN
        CreateUser createUser = createUserHandler.handler(useCase);
        //THEN
        assertTrue(createUser.getIsCreated());
        assertNotNull(createUser.getAccessToken());

    }

    @Test
    void Should_ReturnIsCreatedFalse_When_InvalidUser() {
        //GIVEN
        CreateUserUseCase useCase = new CreateUserUseCase("ahmet", "emre@emre.com");
        //WHEN
        CreateUser createUser = createUserHandler.handler(useCase);
        //THEN
        assertFalse(createUser.getIsCreated());
        assertNull(createUser.getAccessToken());
    }

}
