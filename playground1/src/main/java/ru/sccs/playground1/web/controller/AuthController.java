package ru.sccs.playground1.web.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.sccs.playground1.repository.UserRepository;
import ru.sccs.playground1.web.dto.user.UserCreationDTO;
import ru.sccs.playground1.web.mapper.UserMapper;
import ru.sccs.playground1.web.security.JWTUtil;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@Log4j2
//@CrossOrigin(origins = "*")
public class AuthController {

//    private final AuthService authService;
//    private final UserService userService;

    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
//    @CrossOrigin(origins = "*")
    public Map<String, String> login(/*@Validated @RequestBody JwtRequest loginRequest*/
    @RequestBody UserCreationDTO userCreationDTO, HttpServletResponse response) {
//        return authService.login(loginRequest);
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userCreationDTO.getUsername(), userCreationDTO.getPassword()
                        )
                );
        log.info(authenticate);
        String token = jwtUtil.generateToken(userCreationDTO.getUsername());
        ResponseCookie cookie = ResponseCookie.from("Token", token)
//                .httpOnly(true)
                .sameSite("Strict")
                .secure(false)
                .path("/")
                .maxAge(86400)
                .build();
//        Cookie cookie = new Cookie("token", "token");
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        cookie.setMaxAge(864000);
//        cookie.setDomain("localhost");
        response.addHeader("Set-Cookie", cookie.toString());
        return Map.of("token", token);
//        return new JwtResponse();
    }

    @PostMapping("/register")
//    @CrossOrigin(origins = "*")
    public Long register(@RequestBody UserCreationDTO userCreationDTO) {
        log.info(userCreationDTO);
        userCreationDTO.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));
        return userRepository.save(userMapper.toEntity(userCreationDTO)).getId();
//        return jwtUtil.generateToken(userCreationDTO.getUsername());
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
