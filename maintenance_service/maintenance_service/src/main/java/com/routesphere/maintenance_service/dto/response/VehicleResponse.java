package com.routesphere.maintenance_service.dto.response;

import com.routesphere.maintenance_service.enums.VehicleStatus;
import lombok.Data;

@Data
public class VehicleResponse {

    private Long vehicleId;
    private String vehicleNumber;
    private String vehicleType;
    private VehicleStatus vehicleStatus;
}
