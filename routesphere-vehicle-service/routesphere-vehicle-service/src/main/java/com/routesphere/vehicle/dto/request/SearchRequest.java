package com.routesphere.vehicle.dto.request;

import com.routesphere.vehicle.dto.response.LogResponse;
import com.routesphere.vehicle.dto.response.MaintenanceResponse;
import com.routesphere.vehicle.dto.response.ShipmentResponse;
import com.routesphere.vehicle.dto.response.TripResponse;
import com.routesphere.vehicle.enums.FuelType;
import com.routesphere.vehicle.enums.VehicleStatus;
import com.routesphere.vehicle.enums.VehicleType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchRequest {

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
