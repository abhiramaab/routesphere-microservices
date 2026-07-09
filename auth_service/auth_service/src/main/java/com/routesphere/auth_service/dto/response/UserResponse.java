package com.routesphere.auth_service.dto.response;

import com.routesphere.auth_service.enums.Role;
import lombok.Data;

@Data
public class UserResponse {

    private String username;
    private String password;
    private String email;
    private Role role;
}
