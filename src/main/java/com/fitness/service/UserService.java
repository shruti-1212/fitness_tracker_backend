package com.fitness.service;

import com.fitness.dto.UserDTO;
import com.fitness.entity.User;
import com.fitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // Simple in-memory session storage (in production, use proper session management)
    private Map<String, Long> userSessions = new HashMap<>();
    
    public User registerUser(UserDTO userDTO) {
        // Check if username already exists
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        // Check if email already exists
        if (userDTO.getEmail() != null && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        // Create new user
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        return userRepository.save(user);
    }
    
    public String loginUser(UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findByUsername(userDTO.getUsername());
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(userDTO.getPassword())) {
                // Generate simple session token (in production, use JWT or proper session management)
                String sessionToken = "session_" + user.getId() + "_" + System.currentTimeMillis();
                userSessions.put(sessionToken, user.getId());
                return sessionToken;
            }
        }
        
        throw new RuntimeException("Invalid username or password");
    }
    
    public User getUserFromSession(String sessionToken) {
        Long userId = userSessions.get(sessionToken);
        if (userId == null) {
            throw new RuntimeException("Invalid session");
        }
        
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        return userOpt.get();
    }
    
    public void logoutUser(String sessionToken) {
        userSessions.remove(sessionToken);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
} 