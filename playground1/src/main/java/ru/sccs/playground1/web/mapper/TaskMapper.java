package ru.sccs.playground1.web.mapper;

import org.mapstruct.Mapper;
import ru.sccs.playground1.domain.task.Task;
import ru.sccs.playground1.web.dto.task.TaskDTO;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskDTO toDto(Task task);

    List<TaskDTO> toDto(List<Task> tasks);

    Task toEntity(TaskDTO dto);

}
