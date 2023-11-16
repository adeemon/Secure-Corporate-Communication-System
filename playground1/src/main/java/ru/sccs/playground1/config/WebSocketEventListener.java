//package ru.sccs.playground1.config;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.context.event.EventListener;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.messaging.SessionDisconnectEvent;
//import ru.sccs.playground1.web.controller.ChatMessage;
//import ru.sccs.playground1.web.controller.MessageType;
//
//@Component
//@RequiredArgsConstructor
//@Log4j2
//public class WebSocketEventListener {
//
//    private final SimpMessageSendingOperations messageTemplate;
//
//    @EventListener
//    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        if (username != null) {
//            log.info("User disconnected: {}", username);
//            var chatMessage = ChatMessage.builder()
//                    .type(MessageType.LEAVE)
////                    .content()
//                    .sender(username)
//                    .build();
//            messageTemplate.convertAndSend("/topic/public", chatMessage);
//        }
//    }
//
//}
