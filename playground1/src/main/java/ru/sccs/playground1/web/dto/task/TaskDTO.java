package ru.sccs.playground1.web.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.sccs.playground1.domain.task.Status;
import ru.sccs.playground1.web.dto.validation.OnCreate;
import ru.sccs.playground1.web.dto.validation.OnUpdate;

import java.time.LocalDateTime;

@Data
public class TaskDTO {

    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotNull(groups = {OnUpdate.class, OnCreate.class})
    private String title;
    private String description;
    private Status status;
    private LocalDateTime expirationDate;


}
