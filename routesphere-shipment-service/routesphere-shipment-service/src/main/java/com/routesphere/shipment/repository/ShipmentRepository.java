package com.routesphere.shipment.repository;

import com.routesphere.shipment.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    List<Shipment> findByVehicleId(Long vehicleId);
    List<Shipment> findByCustomerId(Long customerId);

}
