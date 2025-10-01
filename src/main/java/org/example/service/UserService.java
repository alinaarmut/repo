package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public User register(String username, String rawPassword, String displayName) {
        User u = new User();
        u.setUsername(username);
        u.setPasswordHash(passwordEncoder.encode(rawPassword));
        return userRepository.save(u);
    }

    public Optional<User> findByUsername(String username) { return userRepository.findByUsername(username); }

    public boolean checkPassword(String raw, String hash) { return passwordEncoder.matches(raw, hash); }
}

