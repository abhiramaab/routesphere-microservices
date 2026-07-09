package com.routesphere.trip_service.entity;


import com.routesphere.trip_service.enums.TripStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "trip")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tripNumber;
    private String startLocation;
    private String endLocation;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private Double distance;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;


    private Long vehicleId;


    private Long shipmentId;


    private Long invoiceId;

    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;

    private String vehicleNumber;
}
