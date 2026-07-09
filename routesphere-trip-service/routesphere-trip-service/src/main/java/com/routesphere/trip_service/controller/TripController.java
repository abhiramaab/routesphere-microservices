package com.routesphere.trip_service.controller;

import com.routesphere.trip_service.dto.request.CreateTripRequest;
import com.routesphere.trip_service.dto.request.SearchRequest;
import com.routesphere.trip_service.dto.response.TripResponse;
import com.routesphere.trip_service.service.TripService;
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
@RequestMapping("/api/trip")
@RequiredArgsConstructor
@Tag(name = "Trip")
public class TripController {

    private final TripService tripService;

    @PostMapping
    @Operation(summary = "Create a trip record")
    public TripResponse createTrip(@Valid @RequestBody CreateTripRequest request){
        return tripService.createTrip(request);
    }

    @GetMapping("/{tripId}")
    @Operation(summary = "retrieve a trip record by Id")
    public TripResponse getTripById(@PathVariable Long tripId){
        return tripService.getTripById(tripId);
    }

    @PutMapping("/{tripId}")
    @Operation(summary = "Update a trip record by Id")
    public TripResponse updateTripById(@PathVariable Long tripId,@Valid @RequestBody CreateTripRequest request){
        return tripService.updateTripById(tripId, request);
    }

    @DeleteMapping("/{tripId}")
    @Operation(summary = "Delete a trip record by Id")
    public String deleteTripById(@PathVariable Long tripId){
        return tripService.deleteTripById(tripId);
    }

    @GetMapping("/vehicle/{vehicleId}")
    @Operation(summary = "Retrieve a trip record by vehicle Id")
    public List<TripResponse> findByVehicleId(@PathVariable Long vehicleId){
        return tripService.findVehicleById(vehicleId);
    }

    @PostMapping("/search")
    @Operation(summary = "Search trip records using one or more criteria")
    public List<TripResponse> searchTrip(@RequestBody SearchRequest request){
        return tripService.searchTrip(request);
    }

    @GetMapping
    @Operation(summary = "Retrieve all trip records")
    public Page<TripResponse> findAllTrips(@PageableDefault(page = 0, sort = "id", size = 10, direction = Sort.Direction.ASC) Pageable pageable){
        return tripService.findAllTrip(pageable);
    }
}
