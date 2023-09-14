package com.undina.gateway.service;


import com.undina.gateway.entity.User;
import com.undina.gateway.repository.UserRepository;
import com.undina.gateway.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.JWTToken;
import org.openapitools.model.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public JWTToken login(LoginRequest loginRequest) {
        log.info("login - start: LoginRequest = {}", loginRequest);
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong credentials");
        }
        String token = jwtUtil.generateToken(loginRequest.getEmail());
        JWTToken jwtToken = new JWTToken(loginRequest.getEmail(), token);
        log.info("login - result: jwtToken = {}", jwtToken);
        return jwtToken;
    }

    public JWTToken signup(LoginRequest loginRequest) {
        log.info("signup - start: LoginRequest = {}", loginRequest);
        User user = new User(loginRequest.getEmail(), passwordEncoder.encode(loginRequest.getPassword()));
        User registratedUser = userRepository.save(user);
        String token = jwtUtil.generateToken(registratedUser.getEmail());
        JWTToken jwtToken = new JWTToken(registratedUser.getEmail(), token);
        log.info("signup - result: jwtToken = {}", jwtToken);
        return jwtToken;
    }
}
