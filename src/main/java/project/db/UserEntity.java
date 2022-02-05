package project.db;


import lombok.Getter;
import lombok.NoArgsConstructor;
import project.model.Message;
import project.model.User;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity(name = "users")
@NoArgsConstructor(force = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToMany(targetEntity = MessageEntity.class, fetch = FetchType.LAZY,
           cascade = CascadeType.ALL)
    @JoinColumn(name = "sender", referencedColumnName = "name")
    private List<Message> messages;


    UserEntity(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.messages = new ArrayList<>();
    }

    UserEntity(Long id){
        this.id = id;
    }

    User toDomain(){return User.of(id, name, email, password, messages);}
}
