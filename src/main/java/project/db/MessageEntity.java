package project.db;

import lombok.NoArgsConstructor;
import project.model.Message;
import javax.persistence.*;


import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(force = true)
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String sender;
    private Long created_at;
    private String content;




    MessageEntity(Message message){
        this.id = message.getId();
        this.sender = message.getSender();
        this.content = message.getContent();
        this.created_at = message.getCreated_at();
    }

}
