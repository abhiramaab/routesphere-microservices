package com.routesphere.shipment.serviceImpl;

import com.routesphere.shipment.dto.request.CreateShipmentRequest;
import com.routesphere.shipment.dto.request.SearchRequest;
import com.routesphere.shipment.dto.response.ShipmentResponse;
import com.routesphere.shipment.entity.Shipment;
import com.routesphere.shipment.exception.NotFoundException;
import com.routesphere.shipment.repository.ShipmentRepository;
import com.routesphere.shipment.service.ShipmentService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Override
    public ShipmentResponse createShipment(CreateShipmentRequest request) {
        Shipment shipment = new Shipment();
        shipment.setShipmentPriority(request.getShipmentPriority());
        shipment.setDeliveryDate(request.getDeliveryDate());
        shipment.setPickupLocation(request.getPickupLocation());
        shipment.setDeliveryLocation(request.getDeliveryLocation());
        shipment.setWeight(request.getWeight());
        shipment.setPickupDate(request.getPickupDate());
        shipment.setTrackingNumber(request.getTrackingNumber());
        shipment.setCustomerId(request.getCustomerId());
        shipment.setInvoiceId(request.getInvoiceId());
        shipment.setVehicleId(request.getVehicleId());

        Shipment savedShipment = shipmentRepository.save(shipment);
        return mapToResponse(savedShipment);
    }

    @Override
    public ShipmentResponse updateShipment(Long shipmentId, CreateShipmentRequest request) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow(() -> new NotFoundException("Shipment not found with id: " + shipmentId));
        shipment.setShipmentPriority(request.getShipmentPriority());
        shipment.setDeliveryDate(request.getDeliveryDate());
        shipment.setPickupLocation(request.getPickupLocation());
        shipment.setCustomerId(request.getCustomerId());
        shipment.setDeliveryLocation(request.getDeliveryLocation());
        shipment.setWeight(request.getWeight());
        shipment.setPickupDate(request.getPickupDate());
        shipment.setTrackingNumber(request.getTrackingNumber());
        Shipment updatedShipment = shipmentRepository.save(shipment);
        return mapToResponse(updatedShipment);
    }

    @Override
    public String deleteShipment(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow(() -> new NotFoundException("Shipment not found with id : " + shipmentId));
        shipmentRepository.delete(shipment);
        return "Shipment deleted : " + shipment.getId();
    }

    @Override
    public ShipmentResponse getShipmentById(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow(() -> new NotFoundException("Shipment not found with id : " + shipmentId));
        return mapToResponse(shipment);
    }

    private ShipmentResponse mapToResponse(Shipment shipment) {
        ShipmentResponse shipmentResponse = new ShipmentResponse();
        shipmentResponse.setShipmentId(shipment.getId());
        shipmentResponse.setShipmentPriority(shipment.getShipmentPriority());
        shipmentResponse.setWeight(shipment.getWeight());
        shipmentResponse.setDeliveryDate(shipment.getDeliveryDate());
        shipmentResponse.setPickupDate(shipment.getPickupDate());
        shipmentResponse.setCustomerId(shipment.getCustomerId());
        shipmentResponse.setPickupLocation(shipment.getPickupLocation());
        shipmentResponse.setDeliveryLocation(shipment.getDeliveryLocation());
        shipmentResponse.setTrackingNumber(shipment.getTrackingNumber());

        shipmentResponse.setInvoiceId(shipment.getInvoiceId());
        shipmentResponse.setVehicleId(shipment.getVehicleId());

        return shipmentResponse;
    }

    @Override
    public List<ShipmentResponse> findByVehicleId(Long vehicleId) {

        List<Shipment> logs = shipmentRepository.findByVehicleId(vehicleId);

        return logs.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ShipmentResponse> findBySearch(SearchRequest request) {
        return shipmentRepository.findAll().stream()
                .filter(shipment -> request.getShipmentId() == null ||
                        shipment.getId() == request.getShipmentId())
                .filter(shipment -> request.getShipmentPriority() == null ||
                        shipment.getShipmentPriority() == request.getShipmentPriority())
                .filter(shipment -> request.getPickupDate() == null ||
                        shipment.getPickupDate() == request.getPickupDate())
                .filter(shipment -> request.getWeight() == null ||
                        shipment.getWeight() == request.getWeight())
                .filter(shipment -> request.getDeliveryLocation() == null ||
                        shipment.getDeliveryLocation() == request.getDeliveryLocation())
                .filter(shipment -> request.getPickupLocation() == null ||
                        shipment.getPickupLocation() == request.getPickupLocation())
                .filter(shipment -> request.getDeliveryDate() == null ||
                        shipment.getDeliveryDate() == request.getDeliveryDate())
                .filter(shipment -> request.getTrackingNumber() == null ||
                        shipment.getTrackingNumber() == request.getTrackingNumber())
                .filter(shipment -> request.getVehicleId() == null ||
                        shipment.getVehicleId() == request.getVehicleId())
                .filter(shipment -> request.getInvoiceId() == null ||
                        shipment.getInvoiceId() == request.getInvoiceId())
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ShipmentResponse> findByCustomerId(Long customerId){
        return shipmentRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public Page<ShipmentResponse> findAll(Pageable pageable) {
        return shipmentRepository.findAll(pageable).map(this::mapToResponse);
    }
}
