package com.routesphere.auth_service.dto.request;

import com.routesphere.auth_service.enums.Role;
import lombok.Data;

@Data
public class UpdateRequest {

    private String username;
    private String password;
    private String email;
    private Role role;
}
