package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import project.model.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/mes")
@CrossOrigin
public class MessageController {


    @Autowired
    MessageService messageService;

    @MessageMapping("/chat/{to}")
    public void sendMessageWs(@DestinationVariable Long receiver, Message message){

        messageService.sendMessage(message);
    }


    @GetMapping(path = "/{from}/{to}")
    public List<Map<String, Object>> findAllMessages(@PathVariable("from") String sender) {
        return messageService.findAllMessages(sender);
    }

    @PostMapping
    public String sendMessage(@RequestBody SendMessageRequest message) {
        return messageService.sendMessage(message.toDomain());
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {
        return messageService.deleteMessage(id);
    }

    @DeleteMapping(path = "/delete/user/{sender}")
    public String deleteUserMessages(@PathVariable String sender) {
        return messageService.deleteUserMessages(sender);

    }
}





