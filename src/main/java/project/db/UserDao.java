package project.db;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import project.model.User;
import project.model.UserRepository;
import java.util.Optional;


import static lombok.AccessLevel.PACKAGE;

@Repository
@RequiredArgsConstructor(access = PACKAGE)
public class UserDao implements UserRepository {

    private final UserJpaRepository repository;
    @Override
    public Optional<User> findUser(Long id) {
        Example<UserEntity> example = Example.of(new UserEntity(id));

        return repository.findOne(example).map(UserEntity::toDomain);
    }



    @Override
    public String addUser(User user) {

        UserEntity userEntity = new UserEntity(user);
        return "user added";
    }


    @Override
    public String deleteUser(Long id) {

        repository.deleteById(id);

        return "user deleted";
    }
}
