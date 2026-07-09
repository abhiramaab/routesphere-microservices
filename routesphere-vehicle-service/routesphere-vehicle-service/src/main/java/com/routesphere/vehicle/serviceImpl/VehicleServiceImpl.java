package com.routesphere.vehicle.serviceImpl;

import com.routesphere.vehicle.client.FuelLogClient;
import com.routesphere.vehicle.client.MaintenanceClient;
import com.routesphere.vehicle.client.ShipmentClient;
import com.routesphere.vehicle.client.TripClient;
import com.routesphere.vehicle.dto.request.CreateVehicleRequest;
import com.routesphere.vehicle.dto.request.SearchRequest;
import com.routesphere.vehicle.dto.response.*;
import com.routesphere.vehicle.entity.Vehicle;
import com.routesphere.vehicle.repository.VehicleRepository;
import com.routesphere.vehicle.service.VehicleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final MaintenanceClient maintenanceClient;
    private final FuelLogClient fuelLogClient;
    private final ShipmentClient shipmentClient;
    private final TripClient tripClient;

    @Override
    public VehicleResponse createVehicle(CreateVehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setVehicleCapacity(request.getVehicleCapacity());
        vehicle.setFuelType(request.getFuelType());
        vehicle.setInsuranceExpiry(request.getInsuranceExpiry());
        vehicle.setVehicleStatus(request.getVehicleStatus());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return mapToResponse(savedVehicle);
    }

    @Override
    public VehicleResponse updateVehicle(Long vehicleId, CreateVehicleRequest request) {
        Vehicle updateVehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle Not Found"));
        updateVehicle.setVehicleNumber(request.getVehicleNumber());
        updateVehicle.setVehicleType(request.getVehicleType());
        updateVehicle.setVehicleCapacity(request.getVehicleCapacity());
        updateVehicle.setFuelType(request.getFuelType());
        updateVehicle.setInsuranceExpiry(request.getInsuranceExpiry());
        updateVehicle.setVehicleStatus(request.getVehicleStatus());

        Vehicle savedVehicle = vehicleRepository.save(updateVehicle);

        return mapToResponse(savedVehicle);


    }

    @Override
    public String deleteVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle Not Found"));
        vehicleRepository.deleteById(vehicleId);
        return "Vehicle Deleted Successfully : " + vehicle.getVehicleNumber();
    }

    @Override
    public VehicleResponse findVehicleById(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle Not Found"));
        return mapToResponse(vehicle);
    }

    @Override
    public VehicleResponse findByFuelId(Long fuelId) {
        Vehicle vehicle = vehicleRepository.findByFuelId(fuelId).orElseThrow(() -> new RuntimeException("FuelId with : " + fuelId + " not found"));
        return mapToResponse(vehicle);
    }

    @Override
    public VehicleResponse findByShipmentId(Long shipmentId) {
        Vehicle vehicle = vehicleRepository.findByShipmentId(shipmentId).orElseThrow(() -> new RuntimeException("Shipment Id with : " + shipmentId + " not found"));
        return mapToResponse(vehicle);
    }

    @Override
    public VehicleResponse findByTripId(Long tripId) {
        Vehicle vehicle = vehicleRepository.findByTripId(tripId).orElseThrow(() -> new RuntimeException("TripId with : " + tripId + " not found"));
        return mapToResponse(vehicle);
    }

    @Override
    public VehicleResponse findByMaintenanceId(Long maintenanceId) {
        Vehicle vehicle = vehicleRepository.findByMaintenanceId(maintenanceId).orElseThrow(() -> new RuntimeException("Maintenance Id with : " + maintenanceId + " not found"));
        return mapToResponse(vehicle);
    }

    @Override
    public List<VehicleResponse> searchVehicle(SearchRequest request) {
        return vehicleRepository.findAll().stream()
                .filter(vehicle -> request.getVehicleId() == null ||
                        vehicle.getId() == request.getVehicleId())
                .filter(vehicle -> request.getVehicleCapacity() == null ||
                        vehicle.getVehicleCapacity() == request.getVehicleCapacity())
                .filter(vehicle -> request.getVehicleStatus() == null ||
                        vehicle.getVehicleStatus() == request.getVehicleStatus())
                .filter(vehicle -> request.getVehicleNumber() == null ||
                        Objects.equals(vehicle.getVehicleNumber(), request.getVehicleNumber()))
                .filter(vehicle -> request.getFuelType() == null ||
                        vehicle.getFuelType() == request.getFuelType())
                .filter(vehicle -> request.getInsuranceExpiry() == null ||
                        Objects.equals(vehicle.getInsuranceExpiry(), request.getInsuranceExpiry()))
                .filter(vehicle -> request.getFuelId() == null ||
                        Objects.equals(vehicle.getFuelId(), request.getFuelId()))
                .filter(vehicle -> request.getTripId() == null ||
                        Objects.equals(vehicle.getTripId(), request.getTripId()))
                .filter(vehicle -> request.getShipmentId() == null ||
                        Objects.equals(vehicle.getShipmentId(), request.getShipmentId()))
                .filter(vehicle -> request.getMaintenanceId() == null ||
                        Objects.equals(vehicle.getMaintenanceId(), request.getMaintenanceId()))
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public Page<VehicleResponse> findAll(Pageable pageable) {
        return vehicleRepository.findAll(pageable).map(this::mapToResponse);
    }

    private VehicleResponse mapToResponse(Vehicle vehicle){
        VehicleResponse vehicleResponse = new VehicleResponse();

        vehicleResponse.setVehicleId(vehicle.getId());
        vehicleResponse.setVehicleNumber(vehicle.getVehicleNumber());
        vehicleResponse.setVehicleType(vehicle.getVehicleType());
        vehicleResponse.setVehicleCapacity(vehicle.getVehicleCapacity());
        vehicleResponse.setFuelType(vehicle.getFuelType());
        vehicleResponse.setInsuranceExpiry(vehicle.getInsuranceExpiry());
        vehicleResponse.setVehicleStatus(vehicle.getVehicleStatus());

        if (vehicle.getMaintenanceId() != null) {
            MaintenanceResponse maintenance = maintenanceClient.findByMaintenanceId(vehicle.getMaintenanceId());
            vehicleResponse.setMaintenanceId(maintenance);
        }

        if (vehicle.getFuelId() != null) {
            LogResponse log = fuelLogClient.findByFuelId(vehicle.getFuelId());
            vehicleResponse.setFuelId(log);
        }

        if (vehicle.getShipmentId() != null) {
            ShipmentResponse shipment = shipmentClient.findByShipmentId(vehicle.getShipmentId());
            vehicleResponse.setShipmentId(shipment);
        }

        if (vehicle.getTripId() != null) {
            TripResponse trip = tripClient.getTripById(vehicle.getTripId());
            vehicleResponse.setTripId(trip);
        }

        return vehicleResponse;
    }
}
