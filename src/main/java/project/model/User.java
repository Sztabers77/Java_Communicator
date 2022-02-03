package project.model;

import lombok.Data;
import java.util.List;

@Data(staticConstructor = "of")
public class User {

    private final Long id;

    private final String name;

    private final String email;

    private final String password;


    private final List<Message> messages;

}
