package com.routesphere.trip_service.service;

import com.routesphere.trip_service.dto.request.CreateTripRequest;
import com.routesphere.trip_service.dto.request.SearchRequest;
import com.routesphere.trip_service.dto.response.TripResponse;
import com.routesphere.trip_service.dto.response.VehicleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TripService {

    TripResponse createTrip(CreateTripRequest request);
    TripResponse getTripById(Long id);
    TripResponse updateTripById(Long id, CreateTripRequest request);
    String deleteTripById(Long id);
    List<TripResponse> searchTrip(SearchRequest request);


    Page<TripResponse> findAllTrip(Pageable pageable);
    List<TripResponse> findVehicleById(Long vehicleId);
    List<TripResponse> findByVehicleNumber(String vehicleNumber);
}
