package com.routesphere.fuellog_service.serviceImpl;

import com.routesphere.fuellog_service.client.ShipmentClient;
import com.routesphere.fuellog_service.client.TripClient;
import com.routesphere.fuellog_service.client.VehicleClient;
import com.routesphere.fuellog_service.dto.ShipmentResponse;
import com.routesphere.fuellog_service.dto.VehicleResponse;
import com.routesphere.fuellog_service.dto.request.CreateLogRequest;
import com.routesphere.fuellog_service.dto.LogResponse;
import com.routesphere.fuellog_service.dto.TripResponse;
import com.routesphere.fuellog_service.dto.request.SearchRequest;
import com.routesphere.fuellog_service.entity.FuelLog;
import com.routesphere.fuellog_service.repository.FuelLogRepository;
import com.routesphere.fuellog_service.service.FuelLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FuelLogServiceImpl implements FuelLogService {

    private final FuelLogRepository fuelLogRepository;
    private final VehicleClient vehicleClient;
    private final ShipmentClient shipmentClient;
    private final TripClient tripClient;

    @Override
    public LogResponse createLog(CreateLogRequest request) {

        TripResponse trip = tripClient.getTrip(request.getTripId());
        if (trip == null) {
            throw new RuntimeException("Trip not found with id : " + request.getTripId());
        }

        VehicleResponse vehicle = vehicleClient.findVehicleById(trip.getVehicleId());
        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found with id : " + trip.getVehicleId());
        }

        ShipmentResponse shipment = shipmentClient.findShipmentById(trip.getShipmentId());
        if (shipment == null) {
            throw new RuntimeException("Shipment not found with id : " + trip.getShipmentId());
        }

        FuelLog fuelLog = new FuelLog();
        fuelLog.setTripId(trip.getTripId());
        fuelLog.setVehicleId(trip.getVehicleId());
        fuelLog.setShipmentId(trip.getShipmentId());
        fuelLog.setFuelQuantity(request.getFuelQuantity());
        fuelLog.setFuelCost(request.getFuelCost());
        fuelLog.setFuelStation(request.getFuelStation());
        fuelLog.setFuelDate(request.getFuelDate());

        FuelLog savedFuelLog = fuelLogRepository.save(fuelLog);
        return mapToLogResponse(savedFuelLog);
    }

    @Override
    public LogResponse updateLog(Long id, CreateLogRequest request) {
        FuelLog fuelLog = fuelLogRepository.findById(id).orElseThrow(() -> new RuntimeException("FuelLog not found"));

        TripResponse trip = tripClient.getTrip(request.getTripId());
        if (trip == null) {
            throw new RuntimeException("Trip not found with id");
        }

        VehicleResponse vehicle = vehicleClient.findVehicleById(trip.getVehicleId());
        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found with id : " + trip.getVehicleId());
        }

        ShipmentResponse shipment = shipmentClient.findShipmentById(trip.getShipmentId());
        if (shipment == null) {
            throw new RuntimeException("Shipment not found with id : " + trip.getShipmentId());
        }

        fuelLog.setTripId(trip.getTripId());
        fuelLog.setFuelDate(request.getFuelDate());
        fuelLog.setVehicleId(trip.getVehicleId());
        fuelLog.setShipmentId(trip.getShipmentId());
        fuelLog.setFuelQuantity(request.getFuelQuantity());
        fuelLog.setFuelCost(request.getFuelCost());
        fuelLog.setFuelStation(request.getFuelStation());
        FuelLog updatedFuelLog = fuelLogRepository.save(fuelLog);
        return mapToLogResponse(updatedFuelLog);
    }

    @Override
    public String deleteLog(Long id) {
        FuelLog fuelLog = fuelLogRepository.findById(id).orElseThrow(() -> new RuntimeException("FuelLog not found"));
        fuelLogRepository.delete(fuelLog);
        return "FuelLog deleted successfully : " + fuelLog.getId();
    }

    @Override
    public LogResponse getLog(Long id) {
        FuelLog fuelLog = fuelLogRepository.findById(id).orElseThrow(() -> new RuntimeException("FuelLog not found"));
        return mapToLogResponse(fuelLog);
    }

    @Override
    public Page<LogResponse> getAllLogs(Pageable pageable) {
        return fuelLogRepository.findAll(pageable).map(this::mapToLogResponse);
    }

    @Override
    public List<LogResponse> findByVehicleId(Long vehicleId) {
        return fuelLogRepository.findByVehicleId(vehicleId)
                .stream()
                .map(this::mapToLogResponse)
                .toList();
    }



    @Override
    public List<LogResponse> findByShipmentId(Long shipmentId) {

        return fuelLogRepository.findByShipmentId(shipmentId)
                .stream()
                .map(this::mapToLogResponse)
                .toList();
    }

    @Override
    public List<LogResponse> findByTripId(Long tripId) {
        return fuelLogRepository.findByTripId(tripId)
                .stream()
                .map(this::mapToLogResponse)
                .toList();
    }

    @Override
    public List<LogResponse> searchLog(SearchRequest request) {
        return fuelLogRepository.findAll().stream()
                .filter(fuelLog -> request.getFuelId() == null ||
                        fuelLog.getId() == request.getFuelId())
                .filter(fuelLog -> request.getFuelCost() == null ||
                        fuelLog.getFuelCost().compareTo(request.getFuelCost()) == 0)
                .filter(fuelLog -> request.getFuelQuantity() == null ||
                        Objects.equals(fuelLog.getFuelQuantity(), request.getFuelQuantity()))
                .filter(fuelLog -> request.getFuelDate() == null ||
                        Objects.equals(fuelLog.getFuelDate(), request.getFuelDate()))
                .filter(fuelLog -> request.getShipmentId() == null ||
                        fuelLog.getShipmentId() == request.getShipmentId())
                .filter(fuelLog -> request.getFuelStation() == null ||
                        Objects.equals(fuelLog.getFuelStation(), request.getFuelStation()))
                .filter(fuelLog -> request.getVehicleId() == null ||
                        fuelLog.getVehicleId() == request.getVehicleId())
                .filter(fuelLog -> request.getTripId() == null ||
                        fuelLog.getTripId() == request.getTripId())
                .map(this::mapToLogResponse)
                .toList();
    }

    @Override
    public Page<LogResponse> findAll(Pageable pageable) {
        return fuelLogRepository.findAll(pageable).map(this::mapToLogResponse);
    }


    private LogResponse mapToLogResponse(FuelLog fuelLog) {

        LogResponse logResponse = new LogResponse();
        logResponse.setFuelId(fuelLog.getId());
        logResponse.setFuelQuantity(fuelLog.getFuelQuantity());
        logResponse.setFuelCost(fuelLog.getFuelCost());
        logResponse.setFuelStation(fuelLog.getFuelStation());
        logResponse.setFuelDate(fuelLog.getFuelDate());

        if (fuelLog.getVehicleId() != null) {
            VehicleResponse vehicle = vehicleClient.findVehicleById(fuelLog.getVehicleId());
            logResponse.setVehicleId(vehicle);
        }

        if (fuelLog.getShipmentId() != null) {
            ShipmentResponse shipment = shipmentClient.findShipmentById(fuelLog.getShipmentId());
            logResponse.setShipmentId(shipment);
        }
        return logResponse;

    }
}
