package com.routesphere.driver_service.dto.request;

import com.routesphere.driver_service.enums.DriverStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchRequest {

    private String driverName;
    private String driverPhone;
    private String driverLicenseNumber;
    private LocalDate driverLicenseExpiry;
    private Double experienceYears;

    private Double minExperience;
    private Double maxExperience;

    private DriverStatus driverStatus;
}
