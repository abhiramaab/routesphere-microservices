package com.routesphere.shipment.service;

import com.routesphere.shipment.dto.request.CreateShipmentRequest;
import com.routesphere.shipment.dto.request.SearchRequest;
import com.routesphere.shipment.dto.response.ShipmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShipmentService {

    ShipmentResponse createShipment(CreateShipmentRequest request);
    ShipmentResponse updateShipment(Long shipmentId, CreateShipmentRequest request);
    String deleteShipment(Long shipmentId);
    ShipmentResponse getShipmentById(Long shipmentId);

    List<ShipmentResponse> findByVehicleId(Long vehicleId);
    List<ShipmentResponse> findBySearch(SearchRequest request);

    List<ShipmentResponse> findByCustomerId(Long customerId);
    Page<ShipmentResponse> findAll(Pageable pageable);
}
