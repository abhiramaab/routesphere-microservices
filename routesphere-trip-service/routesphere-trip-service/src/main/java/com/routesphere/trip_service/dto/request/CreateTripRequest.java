package com.routesphere.trip_service.dto.request;

import com.routesphere.trip_service.enums.TripStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTripRequest {

    @NotNull(message = "Vehicle id cannot be empty")
    private Long vehicleId;

    @NotNull(message = "Shipment hicle id cannot be empty")
    private Long shipmentId;

    @NotNull(message = "Invoice id cannot be empty")
    private Long invoiceId;

    @NotBlank(message = "Trip number cannot be empty")
    @Size(min = 4, max = 20, message = "Trip number must be atleast 4 characters and should not exceed 20 characters")
    private String tripNumber;

    @NotBlank(message = "Start location cannot be empty")
    private String startLocation;

    @NotBlank(message = "End location cannot be empty")
    private String endLocation;

    @NotNull(message = "Distance cannot be empty")
    private Double distance;

    @NotNull(message = "Trip status cannot be empty")
    private TripStatus tripStatus;
}
