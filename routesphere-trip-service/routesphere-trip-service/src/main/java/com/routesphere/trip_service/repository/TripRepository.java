package com.routesphere.trip_service.repository;

import com.routesphere.trip_service.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByVehicleId(Long vehicleId);
    List<Trip> findByVehicleNumber(String vehicleNumber);
}
