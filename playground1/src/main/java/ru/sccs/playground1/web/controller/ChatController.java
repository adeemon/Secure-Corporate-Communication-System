package ru.sccs.playground1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;
import ru.sccs.playground1.domain.task.ChatMessage;

@Controller
@RequiredArgsConstructor
@Log4j2
//@CrossOrigin(origins = "*", exposedHeaders = "Access-Control-Allow-Origin")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
//        return chatMessage;
//    }
//
//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }

//    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/taskChat.{taskId}.send")
    @SendTo("/taskChat.{taskId}")
    public String receive(@Payload String message, @DestinationVariable("taskId") String taskId) {
//        messagingTemplate.convertAndSend("taskChat/task/"+taskId, message);
        log.info("received " + message);
        return message;
    }

}
