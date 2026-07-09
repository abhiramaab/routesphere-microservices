package com.routesphere.trip_service.clients;

import com.routesphere.trip_service.dto.response.VehicleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ROUTESPHERE-VEHICLE")
public interface VehicleClient {

    @GetMapping("/api/vehicle/{vehicleId}")
    VehicleResponse getVehicle(@PathVariable Long vehicleId);

    @GetMapping("/api/vehicleNumber/{vehicleNumber}")
    VehicleResponse getVehicleNumber(@PathVariable String vehicleNumber);
}
