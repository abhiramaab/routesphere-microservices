package com.routesphere.fuellog_service.dto;

import lombok.Data;

@Data
public class TripResponse {

    private Long tripId;
    private Long vehicleId;
    private Long shipmentId;
}
