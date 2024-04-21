package com.example.biblioteka.services;

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

    public Optional<User> checkUser(String username, String password){
        Optional<User> user = userDAO.getUserByNameIgnoreCase(username);
       if (user.isPresent() && passwordHasher.checkPasswords(password,user.get().getPassword())){
            return user;
        }
        return Optional.empty();
    }

    public boolean isUserExist(String username){
        Optional<User> user = userDAO.getUserByNameIgnoreCase(username);
        return user.isPresent();
    }


}
