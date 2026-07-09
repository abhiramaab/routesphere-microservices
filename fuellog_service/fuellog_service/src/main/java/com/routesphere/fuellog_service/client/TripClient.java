package com.routesphere.fuellog_service.client;

import com.routesphere.fuellog_service.dto.LogResponse;
import com.routesphere.fuellog_service.dto.TripResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ROUTESPHERE-TRIP")
public interface TripClient {

    @GetMapping("/api/trip/{tripId}")
    TripResponse getTrip(@PathVariable Long tripId);
}
