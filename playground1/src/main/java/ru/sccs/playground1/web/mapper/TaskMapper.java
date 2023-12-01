package ru.sccs.playground1.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.sccs.playground1.domain.task.ChatMessage;
import ru.sccs.playground1.domain.task.Status;
import ru.sccs.playground1.domain.task.Task;
import ru.sccs.playground1.domain.user.User;
import ru.sccs.playground1.web.dto.task.TaskCreationDTO;
import ru.sccs.playground1.web.dto.task.TaskDTO;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Mapper
public interface TaskMapper {

    TaskDTO toDto(Task task);

    List<TaskDTO> toDto(List<Task> tasks);

    Task toEntity(TaskDTO dto);

    @Mapping(source = ".", target = "chatMessages", qualifiedByName = "newChatMessages")
    @Mapping(source = ".", target = "assignees", qualifiedByName = "newAssignees")
    @Mapping(source = ".", target = "status", qualifiedByName = "defaultStatus")
    Task toEntity(TaskCreationDTO dto);

    @Named("newChatMessages")
    default ArrayList<ChatMessage> newChatMessages(TaskCreationDTO dto) {
        return new ArrayList<>();
    }

    @Named("newAssignees")
    default LinkedHashSet<User> newAssignees(TaskCreationDTO dto) {
        return new LinkedHashSet<>();
    }

    @Named("defaultStatus")
    default Status defaultStatus(TaskCreationDTO dto) {
        return Status.TODO;
    }

}
