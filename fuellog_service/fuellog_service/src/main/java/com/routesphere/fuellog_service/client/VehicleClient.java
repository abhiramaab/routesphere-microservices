package com.routesphere.fuellog_service.client;

import com.routesphere.fuellog_service.dto.VehicleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ROUTESPHERE-VEHICLE")
public interface VehicleClient {

    @GetMapping("/api/vehicle/{vehicleId}")
    VehicleResponse findVehicleById(@PathVariable Long vehicleId);
}
