package ru.sccs.playground1.web.dto.task;

import lombok.Data;
import ru.sccs.playground1.domain.task.Status;

@Data
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private Status status;

}
