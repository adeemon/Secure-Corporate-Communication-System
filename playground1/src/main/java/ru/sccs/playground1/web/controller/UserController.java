package ru.sccs.playground1.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sccs.playground1.domain.user.User;
import ru.sccs.playground1.repository.UserRepository;
import ru.sccs.playground1.web.dto.user.UserCreationDTO;
import ru.sccs.playground1.web.mapper.UserMapper;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @PostMapping("/createUser")
    public User createUser(@RequestBody UserCreationDTO userCreationDTO) {
        return userRepository.save(userMapper.toEntity(userCreationDTO));
    }

    @GetMapping("/test")
    public String getTest() {
        return "test";
    }

}
