package com.routesphere.fuellog_service.client;

import com.routesphere.fuellog_service.dto.ShipmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ROUTESPHERE-SHIPMENT")
public interface ShipmentClient {

    @GetMapping("/api/shipment/{shipmentId}")
    ShipmentResponse findShipmentById(@PathVariable Long shipmentId);
}
