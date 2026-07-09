package com.routesphere.auth_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @Email(message = "Invalid email")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Min(value = 4, message = "Password must be atleast 4 characters")
    private String password;

    @NotBlank(message = "Username cannot be blank")
    @Max(value = 30, message = "Username cnanot exceed 30 characters")
    private String username;

}
