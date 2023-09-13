package com.undina.gateway.service;

import com.undina.gateway.entity.LoginRequest;
import com.undina.gateway.entity.User;
import com.undina.gateway.entity.UserTo;
import com.undina.gateway.repository.UserRepository;
import com.undina.gateway.security.JWTToken;
import com.undina.gateway.security.JWTUtil;
import com.undina.gateway.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong credentials");
        }
        String token = jwtUtil.generateToken(loginRequest.getEmail());
        return new JWTToken(loginRequest.getEmail(), token);
    }

    public JWTToken signup(LoginRequest loginRequest) {
        User user = new User(loginRequest.getEmail(), passwordEncoder.encode(loginRequest.getPassword()));
        User registratedUser = userRepository.save(user);
        String token = jwtUtil.generateToken(registratedUser.getEmail());
        return new JWTToken(registratedUser.getEmail(), token);
    }

    public UserTo me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof SecurityUser) {
                User user = ((SecurityUser) principal).getUser();
                return new UserTo(user.getEmail(), user.getId());
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong credentials");
    }
//
//    public User getById(String userId) {
//        return userRepository.getUser(userId).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
//    }
}
