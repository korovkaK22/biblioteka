package com.example.biblioteka.web.restcontrollers;

import com.example.biblioteka.dto.LoginDto;
import com.example.biblioteka.dto.SignupDto;
import com.example.biblioteka.entity.User;
import com.example.biblioteka.services.AuthorizationService;
import com.example.biblioteka.services.UserService;
import com.example.biblioteka.security.PasswordHasher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    private final AuthorizationService authorizationService;
    private final UserService userService;
    private final PasswordHasher passwordHasher;

    public AuthorizationController(AuthorizationService authorizationService, UserService userService, PasswordHasher passwordHasher) {
        this.authorizationService = authorizationService;
        this.userService = userService;
        this.passwordHasher = passwordHasher;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDto loginDto) {
        Optional<User> user = authorizationService.checkUser(loginDto.getUsername(), loginDto.getPassword());
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupDto signupDto) {
        if (authorizationService.isUserExist(signupDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // Хешування пароля перед збереженням у базу даних
        String hashedPassword = passwordHasher.getHashedPassword(signupDto.getPassword());

        User newUser = new User();
        newUser.setName(signupDto.getUsername());
        newUser.setPassword(hashedPassword);
        User savedUser = userService.saveUser(newUser);

        // Приховуємо пароль перед поверненням клієнту
        savedUser.setPassword(null);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}

