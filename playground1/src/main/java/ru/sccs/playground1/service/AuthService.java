package ru.sccs.playground1.service;

import ru.sccs.playground1.web.dto.auth.JwtRequest;
import ru.sccs.playground1.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);

}
