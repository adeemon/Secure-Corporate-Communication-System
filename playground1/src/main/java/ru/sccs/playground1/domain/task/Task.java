package ru.sccs.playground1.domain.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.sccs.playground1.repository.UserRepository;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Task {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime expirationDate;

}
