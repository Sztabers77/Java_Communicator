package project.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import project.model.Message;


@Data
@NoArgsConstructor(force = true)
public class SendMessageRequest {

    private Long id;
    private String senderId;
    private String content;
    private Long created_at;



    Message toDomain(){
        return Message.of(id, senderId, created_at,content);
    }
}
