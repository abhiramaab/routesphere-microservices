package com.routesphere.auth_service.serviceImpl;

import com.routesphere.auth_service.dto.request.LoginRequest;
import com.routesphere.auth_service.dto.request.RegisterRequest;
import com.routesphere.auth_service.dto.request.UpdateRequest;
import com.routesphere.auth_service.dto.response.AllUserResponse;
import com.routesphere.auth_service.dto.response.AuthResponse;
import com.routesphere.auth_service.dto.response.UserResponse;
import com.routesphere.auth_service.entity.User;
import com.routesphere.auth_service.enums.Role;
import com.routesphere.auth_service.repository.UserRepository;
import com.routesphere.auth_service.security.JwtUtil;
import com.routesphere.auth_service.service.AuthService;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setRole(Role.EMPLOYEE);
        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);

    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    @Override
    public String deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return "User has been deleted with username: " + user.getUsername();
    }

    @Override
    public UserResponse updateUser(Long userId, UpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        User updatedUser = userRepository.save(user);
        return mapToResponse(updatedUser);
    }

    @Override
    public UserResponse findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return mapToResponse(user);
    }

    @Override
    public AllUserResponse findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return mapToAll(user);
    }

    private UserResponse mapToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        user.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setUsername(user.getUsername());
        userResponse.setRole(user.getRole());
        userResponse.setPassword(passwordEncoder.encode(user.getPassword()));
        return userResponse;
    }

    private AllUserResponse mapToAll(User user) {
        AllUserResponse allUserResponse = new AllUserResponse();
        user.setId(user.getId());
        user.setUsername(user.getUsername());
        return allUserResponse;
    }
}
