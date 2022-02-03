package project.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import project.model.Message;
import project.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Data
@NoArgsConstructor(force = true)
public class UserInsertRequest {

    private Long id;

    private String name;

    private String email;

    private String password;

    private List<MessageDto> messages = new ArrayList<>();


    User toDomain(){

        List<Message> messages= this.messages.stream().map(MessageDto::toDomain).collect(Collectors.toList());
        return User.of(id, name, email, password, messages);}
}




