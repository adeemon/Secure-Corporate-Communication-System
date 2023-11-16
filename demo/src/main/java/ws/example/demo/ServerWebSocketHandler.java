package ws.example.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Log4j2
public class ServerWebSocketHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Server connection closed: {}", status);
        sessions.remove(session);    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Server connection opened");
        sessions.add(session);
        TextMessage message = new TextMessage("one-time message from server");
        log.info("Server sends: {}", message);
        session.sendMessage(message);
    }

    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        log.info("Server received: {}", request);

        String response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(request));
        log.info("Server sends: {}", response);
        session.sendMessage(new TextMessage(response));
    }
}
