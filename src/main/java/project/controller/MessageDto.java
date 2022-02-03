package project.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import project.model.Message;

@Data
@NoArgsConstructor(force = true)
public class MessageDto {

    private final Long Id;
    private final Long senderId;
    private final Long receiverId;
    private final String content;
    private final Long created_at;


    public MessageDto (Message message){
        this.Id = message.getId();
        this.senderId = message.getSenderId();
        this.receiverId = message.getReceiverId();
        this.content = message.getContent();
        this.created_at = message.getCreated_at();

    }

    Message toDomain(){return Message.of(Id, senderId, receiverId, created_at, content);}
}
