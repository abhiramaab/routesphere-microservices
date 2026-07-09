package com.routesphere.maintenance_service.repository;

import com.routesphere.maintenance_service.dto.response.MaintenanceResponse;
import com.routesphere.maintenance_service.entity.Maintenance;
import com.sun.tools.javac.Main;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    List<Maintenance> findByVehicleId(Long vehicleId);
    List<Maintenance> findByVehicleNumber(String vehicleNumber);
}
