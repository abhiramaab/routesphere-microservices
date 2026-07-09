package com.routesphere.auth_service.controller;

import com.routesphere.auth_service.dto.request.LoginRequest;
import com.routesphere.auth_service.dto.request.RegisterRequest;
import com.routesphere.auth_service.dto.request.UpdateRequest;
import com.routesphere.auth_service.dto.response.AllUserResponse;
import com.routesphere.auth_service.dto.response.AuthResponse;
import com.routesphere.auth_service.dto.response.UserResponse;
import com.routesphere.auth_service.entity.User;
import com.routesphere.auth_service.service.AuthService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Data
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable Long userId, @RequestBody UpdateRequest request) {
        return authService.updateUser(userId, request);
    }

    @GetMapping("/{userId}")
    public UserResponse findById(@PathVariable Long userId) {
        return authService.findById(userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        return authService.deleteUser(userId);
    }

    @GetMapping("{username}")
    public AllUserResponse findByUsername(@PathVariable String username) {
        return authService.findByUsername(username);
    }
}
