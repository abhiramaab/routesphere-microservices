package com.routesphere.fuellog_service.controller;

import com.routesphere.fuellog_service.dto.request.CreateLogRequest;
import com.routesphere.fuellog_service.dto.LogResponse;
import com.routesphere.fuellog_service.dto.request.SearchRequest;
import com.routesphere.fuellog_service.service.FuelLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/log")
@RequiredArgsConstructor
@Tag(name = "Fuel Log")
public class FuelLogController {

    private final FuelLogService fuelLogService;

    @PostMapping
    @Operation(summary = "Create a Log")
    public LogResponse createFuelLog(@RequestBody CreateLogRequest request) {
        return fuelLogService.createLog(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a Log")
    public LogResponse getLog(@PathVariable Long id) {
        return fuelLogService.getLog(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Log by Id")
    public LogResponse updateLog(@PathVariable Long id, @RequestBody CreateLogRequest request) {
        return fuelLogService.updateLog(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Log by Id")
    public String deleteLog(@PathVariable Long id) {
        return fuelLogService.deleteLog(id);
    }

    @GetMapping("/vehicle/{vehicleId}")
    @Operation(summary = "Retrieve a Log by vehicle Id")
    public List<LogResponse> findByVehicleId(@PathVariable Long vehicleId) {
        return fuelLogService.findByVehicleId(vehicleId);
    }

    @GetMapping("/shipment/{shipmentId}")
    @Operation(summary = "Retrieve a Log by shipment Id")
    public List<LogResponse> findByShipmentId(@PathVariable Long shipmentId) {
        return fuelLogService.findByShipmentId(shipmentId);
    }

    @GetMapping("/trip/{tripId}")
    @Operation(summary = "Retrieve a Log by trip Id")
    public List<LogResponse> findByTripId(@PathVariable Long tripId) {
        return fuelLogService.findByTripId(tripId);
    }

    @GetMapping
    @Operation(summary = "Retrieve all Logs")
    public Page<LogResponse> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
        return fuelLogService.findAll(pageable);
    }

    @PostMapping("/search")
    @Operation(summary = "Search Log using one or more criteria")
    public List<LogResponse> searchLog(@RequestBody SearchRequest request) {
        return fuelLogService.searchLog(request);
    }
}
