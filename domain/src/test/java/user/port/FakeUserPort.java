package user.port;

import com.example.user.model.CreateUser;
import com.example.user.model.RetrieveUser;
import com.example.user.port.UserPort;
import com.example.user.usecase.CreateUserUseCase;
import com.example.user.usecase.RetrieveUserUseCase;

public class FakeUserPort implements UserPort {
    @Override
    public CreateUser save(CreateUserUseCase useCase) {
        if (useCase.name().equals("emre")) {
            return CreateUser.builder().userId(1L).isCreated(true).build();

        }
        return CreateUser.builder().isCreated(false).build();
    }

    @Override
    public RetrieveUser retrieve(RetrieveUserUseCase useCase) {
        return null;
    }
}
