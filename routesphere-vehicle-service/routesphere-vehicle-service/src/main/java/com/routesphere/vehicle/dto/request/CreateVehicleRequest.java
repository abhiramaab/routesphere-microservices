package com.routesphere.vehicle.dto.request;

import com.routesphere.vehicle.enums.FuelType;
import com.routesphere.vehicle.enums.VehicleStatus;
import com.routesphere.vehicle.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateVehicleRequest {

    @NotBlank(message = "Vehicle number cannot be empty")
    private String vehicleNumber;

    @NotNull(message = "Vehicle capacity cannot be blank")
    private Double vehicleCapacity;

    @NotNull(message = "Insurance expirate date cannot be empty")
    private LocalDate insuranceExpiry;

    @NotNull(message = "Vehicle type cannot be empty")
    private VehicleType vehicleType;

    @NotNull(message = "Fuel type cannot be empty")
    private FuelType fuelType;

    @NotNull(message = "Vehicle status cannot be empty")
    private VehicleStatus vehicleStatus;

}
