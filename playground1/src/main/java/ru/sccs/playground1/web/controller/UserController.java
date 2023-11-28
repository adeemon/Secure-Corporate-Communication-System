package ru.sccs.playground1.web.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sccs.playground1.domain.user.User;
import ru.sccs.playground1.repository.UserRepository;
import ru.sccs.playground1.web.dto.user.UserCreationDTO;
import ru.sccs.playground1.web.mapper.UserMapper;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @GetMapping
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user not found"));
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody UserCreationDTO userCreationDTO) {
        return userRepository.save(userMapper.toEntity(userCreationDTO));
    }

    @GetMapping("/test")
    public String getTest() {
        return "test";
    }

}
