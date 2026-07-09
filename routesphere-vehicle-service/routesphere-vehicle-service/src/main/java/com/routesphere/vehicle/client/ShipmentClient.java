package com.routesphere.vehicle.client;

import com.routesphere.vehicle.dto.response.MaintenanceResponse;
import com.routesphere.vehicle.dto.response.ShipmentResponse;
import com.routesphere.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

import java.util.List;

@EnableFeignClients
public interface ShipmentClient {


    @GetMapping("/api/shipment/{shipmentId}")
    ShipmentResponse findByShipmentId(@PathVariable("shipmentId") Long id);
}
