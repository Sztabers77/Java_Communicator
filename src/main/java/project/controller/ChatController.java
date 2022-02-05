package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import project.model.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller

public class ChatController {

	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;



	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	public ChatMessage register(@Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", message.getSender());
		UserDto user = new UserDto();
		user.setId(1L);
		user.setMessages(new ArrayList<>());
		user.setName(message.getSender());
		user.setEmail("default@email");
		user.setPassword("1234");
		userService.addUser(user.toDomain());

		return message;
	}

	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {



		MessageDto message = new MessageDto();
		message.setId(1L);
		message.setContent(chatMessage.getContent());
		message.setSender(chatMessage.getSender());
		messageService.sendMessage(message.toDomain());

		return chatMessage;
	}

}
