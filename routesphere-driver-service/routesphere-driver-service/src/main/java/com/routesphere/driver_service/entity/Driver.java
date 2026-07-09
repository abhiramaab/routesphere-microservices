package com.routesphere.driver_service.entity;

import com.routesphere.driver_service.enums.DriverStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "driver")
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String driverName;
    private String driverPhone;
    private String driverLicenseNumber;
    private LocalDate driverLicenseExpiry;
    private Double experienceYears;

    @Enumerated(EnumType.STRING)
    private DriverStatus driverStatus;
}
