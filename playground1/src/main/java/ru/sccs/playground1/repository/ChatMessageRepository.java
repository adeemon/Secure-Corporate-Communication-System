package ru.sccs.playground1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sccs.playground1.domain.task.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
