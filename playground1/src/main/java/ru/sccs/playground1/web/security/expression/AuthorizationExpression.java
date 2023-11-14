package ru.sccs.playground1.web.security.expression;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.sccs.playground1.domain.task.Task;
import ru.sccs.playground1.domain.user.User;
import ru.sccs.playground1.repository.TaskRepository;
import ru.sccs.playground1.repository.UserRepository;
import ru.sccs.playground1.web.security.SystemUserDetails;

import java.util.List;
import java.util.stream.Collectors;

@Service("authorizationExpression")
@RequiredArgsConstructor
public class AuthorizationExpression {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public boolean canAccessTask(Long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SystemUserDetails user = (SystemUserDetails) authentication.getPrincipal();
        System.out.println(user.getUser().getUsername());
        Task task = taskRepository.findById(taskId).get();
        task.getAssignees().stream().map(User::getUsername).forEach(System.out::println);
        return task.getAssignees().contains(user.getUser());
    }

//    public List<Task> getAssignedTasks() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        SystemUserDetails user = (SystemUserDetails) authentication.getPrincipal();
//        return taskRepository.findAll()
//                .stream()
//                .filter(task ->
//                        task.getAssignees().contains(user.getUser())
//                ).collect(Collectors.toList());
//    }

}
