package ru.sccs.playground1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.sccs.playground1.domain.user.User;
import ru.sccs.playground1.service.AuthService;
import ru.sccs.playground1.service.UserService;
import ru.sccs.playground1.web.dto.auth.JwtRequest;
import ru.sccs.playground1.web.dto.auth.JwtResponse;
import ru.sccs.playground1.web.dto.user.UserDTO;
import ru.sccs.playground1.web.dto.validation.OnCreate;
import ru.sccs.playground1.web.mapper.UserMapper;
import ru.sccs.playground1.web.security.JWTUtil;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Log4j2
public class AuthController {

//    private final AuthService authService;
//    private final UserService userService;

    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;

    @GetMapping("/login")
    public JwtResponse login(/*@Validated @RequestBody JwtRequest loginRequest*/) {
//        return authService.login(loginRequest);
        return new JwtResponse();
    }

//    @PostMapping("/register")
//    public UserDTO register(@Validated(OnCreate.class) @RequestBody UserDTO userDTO) {
////        log.info("register called");
////        User user = userMapper.toEntity(userDTO);
////        User createdUser = userService.create(user);
////        return userMapper.toDto(createdUser);
//    }
//
//    @PostMapping("/refresh")
//    public JwtResponse refresh(@RequestBody String refreshToken) {
////        return authService.refresh(refreshToken);
//    }

}
