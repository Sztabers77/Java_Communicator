package project.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import project.model.Message;

@Data
@NoArgsConstructor(force = true)
public class MessageDto {

    private Long Id;
    private String sender;
    private String content;
    private Long created_at;


    public MessageDto (Message message){
        this.Id = message.getId();
        this.sender = message.getSender();
        this.content = message.getContent();
        this.created_at = message.getCreated_at();

    }

     Message toDomain(){return Message.of(Id, sender, created_at, content);}
}
