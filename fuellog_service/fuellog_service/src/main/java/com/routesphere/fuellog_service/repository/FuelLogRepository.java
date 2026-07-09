package com.routesphere.fuellog_service.repository;

import com.routesphere.fuellog_service.dto.LogResponse;
import com.routesphere.fuellog_service.entity.FuelLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuelLogRepository extends JpaRepository<FuelLog, Long> {

    List<FuelLog> findByVehicleId(Long vehicleId);
    List<FuelLog> findByShipmentId(Long shipmentId);
    List<FuelLog> findByTripId(Long tripId);
}
