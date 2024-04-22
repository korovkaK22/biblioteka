package com.example.biblioteka.services;

import com.example.biblioteka.dto.SignupDto;
import com.example.biblioteka.entity.User;
import com.example.biblioteka.repository.UserRepository;
import com.example.biblioteka.security.PasswordHasher;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AuthorizationService {
    private PasswordHasher passwordHasher;
    private UserRepository userDAO;


    public Optional<User> checkUser(String username, String inputPassword) {
        Optional<User> userOpt = userDAO.getUserByNameIgnoreCase(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String hashedPassword = user.getPassword();
            if (passwordHasher.checkPasswords(inputPassword, hashedPassword)) {
                return userOpt;
            }

            String hashedNewPass = passwordHasher.getHashedPassword(inputPassword);
            System.out.println(passwordHasher.checkPasswords(inputPassword, hashedNewPass));
            user.setPassword(hashedNewPass);
            userDAO.save(user);
        }
        return Optional.empty();
    }

    public void registerNewUser(SignupDto signupDto) throws Exception {
        String password = signupDto.getPassword();
        String hashedPassword = passwordHasher.getHashedPassword(password);

        User newUser = new User();
        newUser.setName(signupDto.getUsername());
        newUser.setPassword(hashedPassword);


        System.out.println(passwordHasher.checkPasswords(password, hashedPassword));

        userDAO.save(newUser);
    }

    public boolean isUserExist(String username) {
        Optional<User> user = userDAO.getUserByNameIgnoreCase(username);
        return user.isPresent();
    }



}
