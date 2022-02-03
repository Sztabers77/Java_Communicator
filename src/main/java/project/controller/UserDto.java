package project.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import project.model.Message;
import project.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(force = true)
public class UserDto {
    private final Long id;

    private final String name;

    private final String email;

    private final String password;


    private final List<MessageDto> messages;

    public UserDto (User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.messages = user.getMessages().stream().map(MessageDto::new).collect(Collectors.toList());

    }

    User toDomain(){

        List<Message> messages = this.messages.stream().map(MessageDto::toDomain).collect(Collectors.toList());
        return User.of(id, name, email, password, messages);}
}
