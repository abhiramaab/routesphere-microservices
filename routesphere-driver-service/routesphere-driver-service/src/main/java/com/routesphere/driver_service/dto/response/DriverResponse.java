package com.routesphere.driver_service.dto.response;

import com.routesphere.driver_service.enums.DriverStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DriverResponse {

    private Long driverId;
    private String driverName;
    private String driverPhone;
    private String driverLicenseNumber;
    private LocalDate driverLicenseExpiry;
    private Double experienceYears;

    private DriverStatus driverStatus;
}
