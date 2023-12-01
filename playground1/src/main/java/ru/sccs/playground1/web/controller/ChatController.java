package ru.sccs.playground1.web.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.sccs.playground1.domain.task.ChatMessage;
import ru.sccs.playground1.domain.task.Task;
import ru.sccs.playground1.repository.ChatMessageRepository;
import ru.sccs.playground1.repository.TaskRepository;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ChatController {

    private final TaskRepository taskRepository;
    private final ChatMessageRepository chatMessageRepository;

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
    @Transactional
    public String receive(@Payload String message, @DestinationVariable("taskId") String taskId) {
//        messagingTemplate.convertAndSend("taskChat/task/"+taskId, message);
        log.info("received " + message);
        Task task = taskRepository.findById(Long.valueOf(taskId))
                .orElseThrow(() -> new IllegalArgumentException("task not found"));
        ChatMessage msg = ChatMessage.builder()
                .content(message)
                .sentAt(LocalDateTime.now().toString())
                .senderId(1L)
                .build();
        task.getChatMessages().add(msg);
        chatMessageRepository.save(msg);
        log.info(task.getChatMessages());
        log.info(msg);
        taskRepository.save(task);
        return message;
    }

}
