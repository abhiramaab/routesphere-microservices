package com.routesphere.fuellog_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateLogRequest {

    @NotNull(message = "Fuel qty cannot be empty")
    @Min(value = 1, message = "Qty cannot be less than 0")
    @Max(value = 100, message = "Qty cannot exceed 100")
    private BigDecimal fuelQuantity;

    @NotNull(message = "Fuel cost cannot be empty")
    private BigDecimal fuelCost;

    @NotBlank(message = "Fuel station cannot be blank")
    private String fuelStation;

    @NotNull(message = "Trip id cannot be empty")
    @Min(value = 1, message = "Value cannot be less than 1")
    @Size(min = 1, message = "Trip id cannot be less than 1")
    private Long tripId;

    @NotNull(message = "Fuel date cannot be empty")
    private LocalDate fuelDate;

}
