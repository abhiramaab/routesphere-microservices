package com.routesphere.vehicle.dto.response;

import com.routesphere.vehicle.enums.FuelType;
import com.routesphere.vehicle.enums.VehicleStatus;
import com.routesphere.vehicle.enums.VehicleType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class VehicleResponse {

    private Long vehicleId;

    private String vehicleNumber;
    private Double vehicleCapacity;

    private VehicleType vehicleType;

    private FuelType fuelType;
    private VehicleStatus vehicleStatus;

    private LocalDate insuranceExpiry;

    private TripResponse tripId;
    private MaintenanceResponse maintenanceId;
    private LogResponse fuelId;
    private ShipmentResponse shipmentId;
}
