package ru.sccs.playground1.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sccs.playground1.service.TaskService;
import ru.sccs.playground1.web.dto.task.TaskDTO;
import ru.sccs.playground1.web.mapper.TaskMapper;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

//    @GetMapping("/{id}")
//    public TaskDTO getById(@PathVariable Long id) {
//
//    }

}
