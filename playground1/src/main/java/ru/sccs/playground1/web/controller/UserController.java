package ru.sccs.playground1.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sccs.playground1.domain.user.User;
import ru.sccs.playground1.repository.UserRepository;
import ru.sccs.playground1.web.dto.user.UserCreationDTO;
import ru.sccs.playground1.web.mapper.UserMapper;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @PostMapping("/createUser")
    public User createUser(@RequestBody UserCreationDTO userCreationDTO) {
        return userRepository.save(userMapper.toEntity(userCreationDTO));
    }

}
