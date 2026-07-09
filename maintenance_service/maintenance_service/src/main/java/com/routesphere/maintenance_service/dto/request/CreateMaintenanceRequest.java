package com.routesphere.maintenance_service.dto.request;

import com.routesphere.maintenance_service.enums.VehicleStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateMaintenanceRequest {

    @NotBlank(message = "ServiceType cannot be blank")
    private String serviceType;

    @NotNull(message = "ServiceCost cannot be blank")
    private BigDecimal serviceCost;

    @CreatedDate
    private LocalDate lastServiceDate;

    @NotNull(message = "Next Service date cannot be empty")
    private LocalDate nextServiceDate;

    private String remarks;

    @NotNull(message = "Vehicle Id cannot be empty")
    private Long vehicleId;

    @NotBlank(message = "Vehicle status cannot be empty")
    private VehicleStatus vehicleStatus;
}
