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
    PasswordHasher passwordHasher;

    public AuthorizationController(AuthorizationService authorizationService, UserService userService, PasswordHasher passwordHasher) {
        this.authorizationService = authorizationService;
        this.passwordHasher= passwordHasher;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDto loginDto) {
        String username = loginDto.getUsername();
        String inputPass = loginDto.getPassword();
        Optional<User> user = authorizationService.checkUser(username, inputPass);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupDto signupDto) {
        if (authorizationService.isUserExist(signupDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        if (signupDto.getUsername() == null || signupDto.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            authorizationService.registerNewUser(signupDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            // Логування або інша обробка помилок
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

