package com.example.vocabflow.controller;

import com.example.vocabflow.entity.User;
import com.example.vocabflow.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        String result = authService.register(user);

        if (!result.equals("Account created successfully")) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", result));
        }

        return ResponseEntity.ok(
                Map.of("message", result)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> loginData,
            HttpSession session) {

        String usernameOrEmail =
                loginData.get("usernameOrEmail");

        String password =
                loginData.get("password");

        User user =
                authService.login(
                        usernameOrEmail,
                        password
                );

        if (user == null) {
            return ResponseEntity
                    .status(401)
                    .body(Map.of(
                            "message",
                            "Invalid username/email or password"
                    ));
        }

        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("fullName", user.getFullName());

        return ResponseEntity.ok(
                Map.of(
                        "message", "Login successful",
                        "username", user.getUsername(),
                        "fullName", user.getFullName()
                )
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {

        session.invalidate();

        return ResponseEntity.ok(
                Map.of("message", "Logged out successfully")
        );
    }

    @GetMapping("/session")
    public ResponseEntity<?> checkSession(
            HttpSession session) {

        Object userId =
                session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity
                    .status(401)
                    .body(Map.of(
                            "authenticated",
                            false
                    ));
        }

        Map<String, Object> response =
                new HashMap<>();

        response.put("authenticated", true);
        response.put(
                "username",
                session.getAttribute("username")
        );
        response.put(
                "fullName",
                session.getAttribute("fullName")
        );

        return ResponseEntity.ok(response);
    }
}