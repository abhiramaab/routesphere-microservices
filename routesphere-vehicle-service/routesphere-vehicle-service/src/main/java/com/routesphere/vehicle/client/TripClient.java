package com.routesphere.vehicle.client;

import com.routesphere.vehicle.dto.response.TripResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@EnableFeignClients
public interface TripClient {

    @GetMapping("/api/trip/{tripId}")
    TripResponse getTripById(@PathVariable Long tripId);
}
