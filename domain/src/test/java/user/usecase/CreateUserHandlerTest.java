package user.usecase;

import com.example.user.model.CreateUser;
import com.example.user.usecase.CreateUserHandler;
import com.example.user.usecase.CreateUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.port.FakeUserPort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateUserHandlerTest {

    private CreateUserHandler createUserHandler;

    @BeforeEach
    void setUp() {
        FakeUserPort userPort = new FakeUserPort();
        createUserHandler = new CreateUserHandler(userPort);
    }

    @Test
    void Should_ReturnIsCreatedTrue_When_ValidUser() {
        //GIVEN
        CreateUserUseCase useCase = new CreateUserUseCase("emre", "emre@emre.com");
        //WHEN
        CreateUser createUser = createUserHandler.handler(useCase);
        //THEN
        assertTrue(createUser.getIsCreated());

    }

    @Test
    void Should_ReturnIsCreatedFalse_When_InvalidUser() {
        //GIVEN
        CreateUserUseCase useCase = new CreateUserUseCase("ahmet", "emre@emre.com");
        //WHEN
        CreateUser createUser = createUserHandler.handler(useCase);
        //THEN
        assertFalse(createUser.getIsCreated());
    }

}
