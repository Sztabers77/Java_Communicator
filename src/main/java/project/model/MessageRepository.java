package project.model;


import java.util.List;
import java.util.Map;

public interface MessageRepository  {

    String sendMessage(Message message);

    List<Map<String,Object>> findAllMessages(String Sender);


    String deleteMessage(Long id);

    String deleteUserMessages(String sender);
}
