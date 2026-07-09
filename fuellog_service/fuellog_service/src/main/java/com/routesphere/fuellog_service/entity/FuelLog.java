package com.routesphere.fuellog_service.entity;

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
@Table(name = "fuelLog")
public class FuelLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal fuelQuantity;
    private BigDecimal fuelCost;
    private String fuelStation;
    private Long vehicleId;
    private Long shipmentId;
    private Long tripId;
    private LocalDate fuelDate;



}
