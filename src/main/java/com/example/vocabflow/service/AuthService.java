package com.example.vocabflow.service;

import com.example.vocabflow.entity.User;
import com.example.vocabflow.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username already exists";
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already exists";
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(user);

        return "Account created successfully";
    }

    public User login(String usernameOrEmail, String password) {

        User user = userRepository
                .findByUsername(usernameOrEmail)
                .orElseGet(() ->
                        userRepository
                                .findByEmail(usernameOrEmail)
                                .orElse(null)
                );

        if (user == null) {
            return null;
        }

        if (!passwordEncoder.matches(
                password,
                user.getPassword()
        )) {
            return null;
        }

        return user;
    }
}