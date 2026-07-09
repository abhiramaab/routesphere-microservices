package com.routesphere.shipment.dto.request;

import com.routesphere.shipment.enums.ShipmentPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateShipmentRequest {

    @NotBlank(message = "Tracking number cannot be blank")
    private String trackingNumber;

    @NotBlank(message = "Pickup location cannot be blank")
    private String pickupLocation;

    @NotBlank(message = "Delivery location cannot be blank")
    private String deliveryLocation;

    @NotNull(message = "Pickup date cannot be blank")
    private LocalDate pickupDate;

    @NotBlank(message = "Delivery date cannot be blank")
    private LocalDate deliveryDate;

    @NotNull(message = "Weight cannot be empty")
    private Double weight;

    @NotNull(message = "Shipment priority cannot be empty")
    private ShipmentPriority shipmentPriority;

    @NotNull(message = "Customer id cannot be empty")
    private Long customerId;

    @NotNull(message = "Vehicle id cannot be empty")
    private Long vehicleId;

    @NotNull(message = "Invoice id cannot be empty")
    private Long invoiceId;
}
