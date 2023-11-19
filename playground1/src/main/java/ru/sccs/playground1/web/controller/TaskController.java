package ru.sccs.playground1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.sccs.playground1.domain.task.ChatMessage;
import ru.sccs.playground1.domain.task.Task;
import ru.sccs.playground1.domain.user.User;
import ru.sccs.playground1.repository.TaskRepository;
import ru.sccs.playground1.repository.UserRepository;
import ru.sccs.playground1.service.TaskService;
import ru.sccs.playground1.web.dto.task.TaskAddAssigneeRequest;
import ru.sccs.playground1.web.dto.task.TaskCreationDTO;
import ru.sccs.playground1.web.dto.task.TaskStatusUpdateRequest;
import ru.sccs.playground1.web.mapper.TaskMapper;
import ru.sccs.playground1.web.security.SystemUserDetails;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Log4j2
//@CrossOrigin(origins = "*", exposedHeaders = "Access-Control-Allow-Origin")
//@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return taskRepository.findAll();
    }

    @GetMapping("/{taskId}")
    @PreAuthorize("@authorizationExpression.canAccessTask(#taskId)")
    public Task getTaskById(@PathVariable Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("no task with id " + taskId));
        log.info(task.getAssignees()
                .stream()
                .map(User::toString)
                .collect(Collectors.toList())
        );
        return task;
    }

    @PostMapping("/createTask")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Task createTask(@RequestBody TaskCreationDTO taskCreationDTO) {
        return taskRepository.save(taskMapper.toEntity(taskCreationDTO));
    }

    @PutMapping("/{taskId}/updateStatus")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Task updateTaskStatus(@PathVariable Long taskId, @RequestBody TaskStatusUpdateRequest updateRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("no task with id " + taskId));
        task.setStatus(updateRequest.getStatus());
        return taskRepository.save(task);
    }

    @PutMapping("/{taskId}/addAssignee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Task addAssignee(@PathVariable Long taskId, @RequestBody TaskAddAssigneeRequest addAssigneeRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("no task with id " + taskId));
        User user = userRepository.findByUsername(addAssigneeRequest.getUsername()).orElseThrow(() -> new IllegalArgumentException("no user with username " + addAssigneeRequest.getUsername()));
        task.getAssignees().add(user);
        return taskRepository.save(task);
    }

    @PutMapping("/{taskId}/addMessage")
    public Task addMessage(@PathVariable Long taskId, @RequestBody ChatMessage chatMessage) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("no task with id " + taskId));
        task.getChatMessages().add(chatMessage);
        return taskRepository.save(task);
    }

}
