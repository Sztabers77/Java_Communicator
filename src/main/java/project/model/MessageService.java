package project.model;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import static lombok.AccessLevel.PACKAGE;

@Service
@RequiredArgsConstructor(access = PACKAGE)
public class    MessageService {

    MessageRepository repository;
    private final UserRepository userRepository;


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String sendMessage(Message message) {
        Date date = new Date();
        Long time = date.getTime();

        Long senderId = message.getSenderId();
        User found = User.of(senderId,"name", "email", "1234", new ArrayList<>());

        jdbcTemplate.update("INSERT INTO messages (receiver_id, created_at, content,  sender_id) " +
                "values ('" + message.getReceiverId() + "','" + time + "','" + message.getContent() + "','" + found.getId() + "')");

        simpMessagingTemplate.convertAndSend("/topic/messages/" + message.getReceiverId(), message);

        return "message sended";

    }

    public List<Map<String,Object>> findAllMessages(Long sender, Long receiver){
        return jdbcTemplate.queryForList("select * from messages where (sender_id='" + sender + "' and receiver_id='" + receiver + "') " +
                "or (receiver_id='" + sender + "' and sender_id='" + receiver + " + ')");
    }


    public String deleteMessage(Long id) {

        jdbcTemplate.update("DELETE FROM messages WHERE id = '" + id + "'" );

        return "message deleted";

    }

    public String deleteUserMessages(Long id){
        jdbcTemplate.update("DELETE FROM messages WHERE sender_id = '" + id + "'" );

        return "messages deleted";
    }
}
