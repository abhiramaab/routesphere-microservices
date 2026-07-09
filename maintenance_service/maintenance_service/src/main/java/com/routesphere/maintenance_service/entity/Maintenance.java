package com.routesphere.maintenance_service.entity;

import com.routesphere.maintenance_service.enums.VehicleStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Data
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceType;
    private BigDecimal serviceCost;
    private LocalDate lastServiceDate;
    private LocalDate nextServiceDate;
    private String remarks;
    private String vehicleNumber;
    private Long vehicleId;

    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;


}


