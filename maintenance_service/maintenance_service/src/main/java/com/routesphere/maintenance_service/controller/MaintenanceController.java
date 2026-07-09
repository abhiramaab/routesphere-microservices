package com.routesphere.maintenance_service.controller;

import com.routesphere.maintenance_service.dto.request.CreateMaintenanceRequest;
import com.routesphere.maintenance_service.dto.request.SearchRequest;
import com.routesphere.maintenance_service.dto.response.MaintenanceResponse;
import com.routesphere.maintenance_service.service.MaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
@Tag(name = "Maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping
    @Operation(summary = "Create a maintenance record")
    public MaintenanceResponse createMaintenance(@Valid @RequestBody CreateMaintenanceRequest request) {
        return maintenanceService.createMaintenance(request);
    }

    @GetMapping("/{maintenanceId}")
    @Operation(summary = "Retrieve maintenance details by Id")
    public MaintenanceResponse getMaintenanceById(@PathVariable Long maintenanceId) {
        return maintenanceService.findMaintenanceById(maintenanceId);
    }

    @PutMapping("/{maintenanceId}")
    @Operation(summary = "Update maintenance details by Id")
    public MaintenanceResponse updateMaintenance(@PathVariable Long maintenanceId,@Valid @RequestBody CreateMaintenanceRequest request) {
        return maintenanceService.updateMaintenance(maintenanceId, request);
    }

    @DeleteMapping("/{maintenanceId}")
    @Operation(summary = "Delete maintenance by Id")
    public String deleteMaintenance(@PathVariable Long maintenanceId) {
        return  maintenanceService.deleteMaintenanceById(maintenanceId);
    }

    @GetMapping("/vehicle/{vehicleId}")
    @Operation(summary = "Retrieve maintenance details by vehicle Id")
    public List<MaintenanceResponse> findByVehicleId(@PathVariable Long vehicleId) {
        return maintenanceService.findByVehicleId(vehicleId);
    }

    @GetMapping("/vehicleNumber/{vehicleNumber}")
    @Operation(summary = "Retrieve maintenance details by vehicle number")
    public List<MaintenanceResponse> findByVehicleNumber(@PathVariable String vehicleNumber) {
        return maintenanceService.findByVehicleNumber(vehicleNumber);
    }

    @PostMapping("/search")
    @Operation(summary = "Search maintenance records by one or more criteria")
    public List<MaintenanceResponse> searchMaintenance(@RequestBody SearchRequest request){
        return maintenanceService.searchMaintenance(request);
    }

    @GetMapping
    @Operation(summary = "Retrieve all maintenance records")
    public Page<MaintenanceResponse> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return maintenanceService.findAllMaintenance(pageable);
    }
}

