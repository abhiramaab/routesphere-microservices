package com.routesphere.customer.client;

import com.routesphere.customer.dto.response.ShipmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ROUTESPHERE-SHIPMENT")
public interface ShipmentClient {

    @GetMapping("/api/shipment/{shipmentId}")
    ShipmentResponse findByShipmentId(@PathVariable Long shipmentId);

    }

