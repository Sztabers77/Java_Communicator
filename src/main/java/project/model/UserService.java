package project.model;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


import static lombok.AccessLevel.PUBLIC;

@Service
@RequiredArgsConstructor(access = PUBLIC)
public class UserService {

    private final UserRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Map<String,Object>> getAllUsers() {
        List<Map<String,Object>> getAllUser=jdbcTemplate.queryForList("SELECT * FROM users;");

        return getAllUser;
    }

    public String addUser(User user) {
        jdbcTemplate.update("INSERT INTO users (name, email, password) VALUES ('" + user.getName() + "','" + user.getEmail() + "','" + user.getPassword() + "');");
        return "user added";
    }

    public Optional<User> findUser(Long id) {
        List<Map<String,Object>> addNewUser=jdbcTemplate.queryForList("SELECT * FROM users WHERE id = '" + id + "';");

        return repository.findUser(id);
    }

    public String deleteUser(Long id) {
        Integer deleteNewUser=jdbcTemplate.update("DELETE FROM users WHERE id = '" + id + "';");

        return "user deleted";
    }
}
