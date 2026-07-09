package com.routesphere.shipment.entity;

import com.routesphere.shipment.enums.ShipmentPriority;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "shipment")
@Data
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
