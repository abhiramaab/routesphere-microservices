package com.routesphere.vehicle.controller;


import com.routesphere.vehicle.dto.request.CreateVehicleRequest;
import com.routesphere.vehicle.dto.request.SearchRequest;
import com.routesphere.vehicle.dto.response.VehicleResponse;
import com.routesphere.vehicle.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
@Tag(name = "Vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    @Operation(summary = "Create a vehicle")
    public VehicleResponse createVehicle(@Valid @RequestBody CreateVehicleRequest request){
        return vehicleService.createVehicle(request);
    }

    @PutMapping("/{vehicleId}")
    @Operation(summary = "Update a vehicle by Id")
    public VehicleResponse updateVehicle(@PathVariable Long vehicleId,@Valid @RequestBody CreateVehicleRequest request){
        return vehicleService.updateVehicle(vehicleId, request);
    }

    @DeleteMapping("/{vehicleId}")
    public String deleteVehicle(@PathVariable Long vehicleId){
        return vehicleService.deleteVehicle(vehicleId);
    }

    @GetMapping("/{vehicleId}")
    @Operation(summary = "Retrieve a vehicle by Id")
    public  VehicleResponse findVehicleById(@PathVariable Long vehicleId){
        return vehicleService.findVehicleById(vehicleId);
    }

    @PostMapping("/search")
    @Operation(summary = "Search vehicle using one or more criteria")
    public List<VehicleResponse> searchVehicle(@RequestBody SearchRequest request){
        return vehicleService.searchVehicle(request);
    }

    @GetMapping
    @Operation(summary = "Retrieve all vehicles")
    public Page<VehicleResponse> findAll(@PageableDefault(sort = "id", size = 10, page = 0, direction = Sort.Direction.ASC) Pageable pageable){
        return vehicleService.findAll(pageable);
    }

}
