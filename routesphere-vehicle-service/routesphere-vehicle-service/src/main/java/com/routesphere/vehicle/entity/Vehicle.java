package com.routesphere.vehicle.entity;

import com.routesphere.vehicle.enums.FuelType;
import com.routesphere.vehicle.enums.VehicleStatus;
import com.routesphere.vehicle.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Data
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNumber;
    private Double vehicleCapacity;

    private LocalDate insuranceExpiry;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;

    private Long fuelId;
    private Long shipmentId;
    private Long tripId;
    private Long maintenanceId;
}
