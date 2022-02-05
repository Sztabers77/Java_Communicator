package project.db;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import project.model.Message;
import project.model.MessageRepository;
import project.model.MessageService;
import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PACKAGE;

@Repository
@RequiredArgsConstructor(access = PACKAGE)
public class MessageDao implements MessageRepository {

    MessageService service;
    @Override
    public String sendMessage(Message message) {
        Example<MessageEntity> example = Example.of(new MessageEntity(message));
        return service.sendMessage(message);
    }

    @Override
    public List<Map<String, Object>> findAllMessages(String sender) {
        return service.findAllMessages(sender);
    }

    @Override
    public String deleteMessage(Long id) {
        return service.deleteMessage(id);
    }

    @Override
    public String deleteUserMessages(String sender) {
        return service.deleteUserMessages(sender);
    }
}
