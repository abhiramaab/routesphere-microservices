package com.routesphere.vehicle.repository;

import com.routesphere.vehicle.dto.response.MaintenanceResponse;
import com.routesphere.vehicle.dto.response.VehicleResponse;
import com.routesphere.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByFuelId(Long fuelId);
    Optional<Vehicle> findByShipmentId(Long shipmentId);
    Optional<Vehicle> findByTripId(Long tripId);
    Optional<Vehicle> findByMaintenanceId(Long maintenanceId);

}
