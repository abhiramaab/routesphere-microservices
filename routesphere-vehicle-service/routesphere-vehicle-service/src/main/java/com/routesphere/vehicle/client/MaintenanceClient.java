package com.routesphere.vehicle.client;

import com.routesphere.vehicle.dto.response.MaintenanceResponse;
import com.routesphere.vehicle.dto.response.VehicleResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@FeignClient
public interface MaintenanceClient {

    @GetMapping("/api/maintenance/{maintenanceId}")
    MaintenanceResponse findByMaintenanceId(@PathVariable Long maintenanceId);

}
