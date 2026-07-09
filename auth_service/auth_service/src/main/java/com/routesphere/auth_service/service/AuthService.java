package com.routesphere.auth_service.service;

import com.routesphere.auth_service.dto.request.LoginRequest;
import com.routesphere.auth_service.dto.request.RegisterRequest;
import com.routesphere.auth_service.dto.request.UpdateRequest;
import com.routesphere.auth_service.dto.response.AllUserResponse;
import com.routesphere.auth_service.dto.response.AuthResponse;
import com.routesphere.auth_service.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    String deleteUser(Long userId);
    UserResponse updateUser(Long userId, UpdateRequest request);
    UserResponse findById(Long userId);
    AllUserResponse findByUsername(String username);
}

