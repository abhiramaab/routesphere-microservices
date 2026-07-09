package com.routesphere.maintenance_service.dto.request;

import com.routesphere.maintenance_service.enums.VehicleStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SearchRequest {

    private Long maintenanceId;

    private String serviceType;
    private BigDecimal serviceCost;
    private LocalDate lastServiceDate;
    private LocalDate nextServiceDate;
    private String remarks;
    private Long vehicleId;
    private VehicleStatus vehicleStatus;
    private String vehicleNumber;
}
