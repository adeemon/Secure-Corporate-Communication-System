package com.example.wsplayground.controller;

import com.example.wsplayground.model.Greeting;
import com.example.wsplayground.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequiredArgsConstructor
public class ChatController {

//    private final SimpMessagingTemplate simpMessagingTemplate;
//
//    @MessageMapping("/message")
//    @SendTo("/chatroom/public")
//    public Message receivePublicMessage(@Payload Message message) {
//        return message;
//    }
//
//    @MessageMapping("/private-message")
//    public Message receivePrivateMessage(@Payload Message message) {
//        simpMessagingTemplate.convertAndSendToUser(message.getReceiver(), "/private", message);
//        return message;
//    }

    @MessageMapping("/hey")
    @SendTo("/topic/greet")
    public Greeting greeting(Message message) {
        return new Greeting("Hey" + HtmlUtils.htmlEscape(message.getContent()));
    }

}
