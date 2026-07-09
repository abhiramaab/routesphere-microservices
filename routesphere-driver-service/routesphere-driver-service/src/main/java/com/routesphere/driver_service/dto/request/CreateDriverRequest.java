package com.routesphere.driver_service.dto.request;

import com.routesphere.driver_service.enums.DriverStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateDriverRequest {

    @NotBlank(message = "Driver name cannot be empty")
    private String driverName;

    @NotBlank(message = "Driver phone cannot be empty")
    @Max(value = 10, message = "Phone number cannot exceed 10 digits")
    @Min(value = 10, message = "Phone number cannot be less than 10 digits")
    private String driverPhone;

    @NotBlank(message = "Driver license number cannot be empty")
    private String driverLicenseNumber;

    @NotNull(message = "Driver license expiry cannot be blank")
    private LocalDate driverLicenseExpiry;

    @NotNull(message = "Driver experience years cannot be empty")
    private Double experienceYears;

    @NotBlank(message = "Driver status cannot be empty")
    private DriverStatus driverStatus;
}
