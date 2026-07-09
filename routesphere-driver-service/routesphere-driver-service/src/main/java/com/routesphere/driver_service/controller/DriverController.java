package com.routesphere.driver_service.controller;

import com.routesphere.driver_service.dto.request.CreateDriverRequest;
import com.routesphere.driver_service.dto.request.SearchRequest;
import com.routesphere.driver_service.dto.response.DriverResponse;
import com.routesphere.driver_service.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
@Tag(name = "Driver Controller")
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    @Operation(summary = "Create a driver")
    public DriverResponse createDriver(@RequestBody CreateDriverRequest request){
        return driverService.createDriver(request);
    }

    @GetMapping("/{driverId}")
    @Operation(summary = "Retrieve a driver")
    public DriverResponse findDriverById(@PathVariable Long driverId){
        return driverService.findDriverById(driverId);
    }

    @DeleteMapping("/{driverId}")
    @Operation(summary = "Delete a driver by Id")
    public String deleteDriverById(@PathVariable Long driverId){
        driverService.deleteDriverById(driverId);
        return "Driver has been deleted : " + driverId;
    }

    @PutMapping("/{driverId}")
    @Operation(summary = "Update a driver by Id")
    public DriverResponse updateDriver(@PathVariable Long driverId, @RequestBody CreateDriverRequest request){
        return driverService.updateDriver(driverId, request);
    }

    @GetMapping("/name/{driverName}")
    @Operation(summary = "Retrieve a driver by name")
    public DriverResponse findDriverByName(@PathVariable String driverName){
        return driverService.findDriverByDriverName(driverName);
    }

    @GetMapping
    @Operation(summary = "Retrieve all drivers")
    public Page<DriverResponse> findAllDrivers(@PageableDefault(size = 10, page = 0, direction = Sort.Direction.ASC, sort = "id") Pageable pageable){
        return driverService.findAllDrivers(pageable);
    }

    @PostMapping("/search")
    @Operation(summary = "Retrieve driver using one or more criteria")
    public List<DriverResponse> searchDriver(@RequestBody SearchRequest request){
        return  driverService.searchDriver(request);
    }


}
