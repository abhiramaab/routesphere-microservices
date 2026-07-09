package com.routesphere.fuellog_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LogResponse {

    private Long fuelId;
    private BigDecimal fuelQuantity;
    private BigDecimal fuelCost;
    private String fuelStation;
    private VehicleResponse vehicleId;
    private ShipmentResponse shipmentId;
    private TripResponse tripId;
    private LocalDate fuelDate;
}
