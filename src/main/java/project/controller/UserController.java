package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.model.User;
import project.model.UserService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping(path="/all")
    public @ResponseBody
    List<Map<String, Object>> getAllUsers() {

        return userService.getAllUsers();
    }
    @GetMapping(path="/{id}")
    public @ResponseBody Optional<User> getUser(
            @PathVariable Long id
    ) {

        return userService.findUser(id);
    }

    @PostMapping
    public String insertUser(@RequestBody UserInsertRequest request){
            return userService.addUser(request.toDomain());
    }


    @DeleteMapping(path = "/delete/{id}")
    public String deleteUser(@PathVariable  Long id){
        return userService.deleteUser(id);
    }


}
