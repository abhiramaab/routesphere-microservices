package com.routesphere.shipment.dto.response;

import com.routesphere.shipment.enums.ShipmentPriority;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ShipmentResponse {

    private Long shipmentId;

    private String trackingNumber;
    private String pickupLocation;
    private String deliveryLocation;
    private LocalDate pickupDate;
    private LocalDate deliveryDate;
    private Double weight;
    private ShipmentPriority shipmentPriority;
    private Long vehicleId;
    private Long invoiceId;
    private Long customerId;
}
