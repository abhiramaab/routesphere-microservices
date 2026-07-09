package com.routesphere.trip_service.dto.response;

import com.routesphere.trip_service.enums.TripStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TripResponse {

    private Long tripId;

    private String tripNumber;
    private String startLocation;
    private String endLocation;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double distance;

    private Long vehicleId;
    private Long shipmentId;
    private Long invoiceId;

    private String vehicleNumber;

    private TripStatus tripStatus;
}
