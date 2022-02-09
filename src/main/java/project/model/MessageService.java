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
    JdbcTemplate jdbcTemplate;

    public String sendMessage(Message message) {
        Date date = new Date();
        Long time = date.getTime();



        jdbcTemplate.update("INSERT INTO messages (sender, created_at, content) " +
                "values ('" + message.getSender() + "','" + time + "','" + message.getContent() + "');");



        return "message sent";

    }

    public List<Map<String,Object>> findAllMessages(String sender){
        return jdbcTemplate.queryForList("SELECT * FROM messages WHERE sender_id='" + sender + "')");
    }


    public String deleteMessage(Long id) {

        jdbcTemplate.update("DELETE FROM messages WHERE id = '" + id + "'" );

        return "message deleted";

    }

    public String deleteUserMessages(String sender){
        jdbcTemplate.update("DELETE FROM messages WHERE sender_id = '" + sender + "'" );

        return "messages deleted";
    }
}
