package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.dto.RegisterRequest;
import com.apps.quantitymeasurement.entity.User;
import com.apps.quantitymeasurement.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {
        validateRegistration(request);

        User user = new User();
        user.setUsername(request.getUsername().trim());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setPhoneNumber(request.getPhoneNumber().trim());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public User login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }

    private void validateRegistration(RegisterRequest request) {
        if (!StringUtils.hasText(request.getUsername()) || request.getUsername().trim().length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters");
        }

        if (!StringUtils.hasText(request.getEmail()) || !request.getEmail().contains("@")) {
            throw new IllegalArgumentException("A valid email is required");
        }

        if (!StringUtils.hasText(request.getPhoneNumber()) || request.getPhoneNumber().trim().length() < 10) {
            throw new IllegalArgumentException("A valid phone number is required");
        }

        if (!StringUtils.hasText(request.getPassword()) || request.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }

        if (userRepository.existsByUsername(request.getUsername().trim())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail().trim().toLowerCase())) {
            throw new IllegalArgumentException("Email already exists");
        }
    }
}
