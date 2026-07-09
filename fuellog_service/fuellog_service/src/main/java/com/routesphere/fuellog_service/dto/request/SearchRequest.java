package com.routesphere.fuellog_service.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SearchRequest {

    private Long fuelId;
    private BigDecimal fuelQuantity;
    private BigDecimal fuelCost;
    private String fuelStation;
    private Long vehicleId;
    private Long shipmentId;
    private Long tripId;
    private LocalDate fuelDate;
}
