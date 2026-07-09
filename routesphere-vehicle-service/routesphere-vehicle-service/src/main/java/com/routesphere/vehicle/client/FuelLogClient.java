package com.routesphere.vehicle.client;

import com.routesphere.vehicle.dto.response.LogResponse;
import com.routesphere.vehicle.dto.response.VehicleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

import java.util.List;

@FeignClient(name = "ROUTESPHERE-FUELLOG")
public interface FuelLogClient {

    @GetMapping("/api/log/{Id}")
    LogResponse findByFuelId(@PathVariable("fuelId") Long id);


}
