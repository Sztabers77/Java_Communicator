package project.model;


import java.util.Optional;




public interface UserRepository  {

    Optional<User> findUser(Long id);

    String addUser(User user);


    String deleteUser(Long id);


}
