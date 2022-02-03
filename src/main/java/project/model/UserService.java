package project.model;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static lombok.AccessLevel.PACKAGE;

@Service
@RequiredArgsConstructor(access = PACKAGE)
public class UserService {

    private final UserRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Map<String,Object>> getAllUsers() {
        List<Map<String,Object>> getAllUser=jdbcTemplate.queryForList("SELECT * FROM user_entity;");

        return getAllUser;
    }

    public String addUser(User user) {
        Integer addNewUser=jdbcTemplate.update("INSERT INTO user_entity (name, email, password) VALUES ('" + user.getName() + "','" + user.getEmail() + "','" + user.getPassword() + "');");
        return "user added";
    }

    public Optional<User> findUser(Long id) {
        List<Map<String,Object>> addNewUser=jdbcTemplate.queryForList("SELECT * FROM user_entity WHERE id = '" + id + "';");

        return repository.findUser(id);
    }

    public String deleteUser(Long id) {
        Integer deleteNewUser=jdbcTemplate.update("DELETE FROM user_entity WHERE id = '" + id + "';");

        return "user deleted";
    }
}
