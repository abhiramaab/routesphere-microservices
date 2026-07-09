package com.routesphere.vehicle.service;

import com.routesphere.vehicle.dto.request.CreateVehicleRequest;
import com.routesphere.vehicle.dto.request.SearchRequest;
import com.routesphere.vehicle.dto.response.LogResponse;
import com.routesphere.vehicle.dto.response.ShipmentResponse;
import com.routesphere.vehicle.dto.response.TripResponse;
import com.routesphere.vehicle.dto.response.VehicleResponse;
import com.routesphere.vehicle.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {

    VehicleResponse createVehicle(CreateVehicleRequest request);
    VehicleResponse updateVehicle(Long vehicleId, CreateVehicleRequest request);
    String deleteVehicle(Long vehicleId);
    VehicleResponse findVehicleById(Long vehicleId);
    VehicleResponse findByFuelId(Long fuelId);
    VehicleResponse findByShipmentId(Long shipmentId);
    VehicleResponse findByTripId(Long tripId);
    VehicleResponse findByMaintenanceId(Long maintenanceId);
    List<VehicleResponse> searchVehicle(SearchRequest request);
    Page<VehicleResponse> findAll(Pageable pageable);
}
