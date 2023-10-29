package ru.sccs.playground1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.sccs.playground1.domain.task.ChatMessage;
import ru.sccs.playground1.domain.task.Task;
import ru.sccs.playground1.domain.user.User;
import ru.sccs.playground1.repository.TaskRepository;
import ru.sccs.playground1.repository.UserRepository;
import ru.sccs.playground1.service.TaskService;
import ru.sccs.playground1.web.dto.task.TaskCreationDTO;
import ru.sccs.playground1.web.mapper.TaskMapper;
import ru.sccs.playground1.web.security.SystemUserDetails;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Log4j2
//@Validated
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    @GetMapping
    @CrossOrigin(origins = "*")
    public List<Task> getAllTasks() {
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return taskRepository.findAll();
    }

    @GetMapping("/{taskId}")
    @CrossOrigin(origins = "*")
    public Task getTaskById(@PathVariable Long taskId) {
        Task task = taskRepository.findById(taskId).get();
        log.info(task.getAssignees()
                .stream()
                .map(User::toString)
                .collect(Collectors.toList())
        );
        return task;
    }

    @PostMapping("/createTask")
    @CrossOrigin(origins = "*")
    public Task createTask(@RequestBody TaskCreationDTO taskCreationDTO) {
        return taskRepository.save(taskMapper.toEntity(taskCreationDTO));
    }

    @PutMapping("/{taskId}/addAssignee/{userId}")
    @CrossOrigin(origins = "*")
    public Task addAssignee(@PathVariable Long taskId, @PathVariable Long userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("no task with id " + taskId));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("no user with id " + userId));
        task.getAssignees().add(user);
        return taskRepository.save(task);
    }

    @PutMapping("/{taskId}/addMessage")
    @CrossOrigin(origins = "*")
    public Task addMessage(@PathVariable Long taskId, @RequestBody ChatMessage chatMessage) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("no task with id " + taskId));
        task.getChatMessages().add(chatMessage);
        return taskRepository.save(task);
    }

}
