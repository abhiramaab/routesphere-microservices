package com.routesphere.maintenance_service.serviceImpl;

import com.routesphere.maintenance_service.client.VehicleClient;
import com.routesphere.maintenance_service.dto.request.CreateMaintenanceRequest;
import com.routesphere.maintenance_service.dto.request.SearchRequest;
import com.routesphere.maintenance_service.dto.response.MaintenanceResponse;
import com.routesphere.maintenance_service.dto.response.VehicleResponse;
import com.routesphere.maintenance_service.entity.Maintenance;
import com.routesphere.maintenance_service.exception.NotFoundException;
import com.routesphere.maintenance_service.repository.MaintenanceRepository;
import com.routesphere.maintenance_service.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    private final VehicleClient vehicleClient;

    @Override
    public MaintenanceResponse createMaintenance(CreateMaintenanceRequest request) {

        VehicleResponse vehicle = vehicleClient.findVehicleById(request.getVehicleId());

        if (vehicle == null) {
            throw  new NotFoundException("Vehicle Not Found for id : " + request.getVehicleId());
        }

        Maintenance maintenance = new Maintenance();
        maintenance.setServiceCost(request.getServiceCost());
        maintenance.setServiceType(request.getServiceType());
        maintenance.setVehicleId(request.getVehicleId());
        maintenance.setVehicleNumber(vehicle.getVehicleNumber());
        maintenance.setNextServiceDate(request.getNextServiceDate());
        maintenance.setLastServiceDate(request.getLastServiceDate());
        maintenance.setVehicleStatus(request.getVehicleStatus());
        maintenance.setRemarks(request.getRemarks());
        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);
        return mapToResponse(savedMaintenance);
    }

    @Override
    public MaintenanceResponse updateMaintenance(Long maintenanceId, CreateMaintenanceRequest request) {

        VehicleResponse vehicle = vehicleClient.findVehicleById(request.getVehicleId());
        if (vehicle == null) {
            throw  new NotFoundException("Vehicle Not Found for id : " + request.getVehicleId());
        }

        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow(() -> new NotFoundException("maintenance not found"));

        maintenance.setServiceType(request.getServiceType());
        maintenance.setServiceCost(request.getServiceCost());
        maintenance.setVehicleId(request.getVehicleId());
        maintenance.setVehicleNumber(vehicle.getVehicleNumber());
        maintenance.setNextServiceDate(request.getNextServiceDate());
        maintenance.setLastServiceDate(request.getLastServiceDate());
        maintenance.setVehicleStatus(request.getVehicleStatus());
        maintenance.setRemarks(request.getRemarks());

        Maintenance updatedMaintenance = maintenanceRepository.save(maintenance);

        return mapToResponse(updatedMaintenance);
    }

    @Override
    public String deleteMaintenanceById(Long maintenanceId) {
        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow(() -> new NotFoundException("Maintenance not found"));
        maintenanceRepository.delete(maintenance);
        return "Maintenance with id: " + maintenanceId + " has been deleted";
    }

    @Override
    public MaintenanceResponse findMaintenanceById(Long maintenanceId) {
        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow(() -> new NotFoundException("Maintenance not found"));
        return mapToResponse(maintenance);
    }

    @Override
    public List<MaintenanceResponse> findByVehicleId(Long vehicleId){

        List<Maintenance> maintenances = maintenanceRepository.findByVehicleId(vehicleId);
        return maintenances.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public Page<MaintenanceResponse> findAllMaintenance(Pageable pageable){
        return maintenanceRepository.findAll(pageable).map(this::mapToResponse);
    }

    @Override
    public List<MaintenanceResponse> searchMaintenance(SearchRequest request) {
        return maintenanceRepository.findAll().stream()
                .filter(maintenance -> request.getMaintenanceId() == null ||
                        maintenance.getId() == request.getMaintenanceId())
                .filter(maintenance -> request.getServiceType() == null ||
                        Objects.equals(maintenance.getServiceType(), request.getServiceType()))
                .filter(maintenance -> request.getServiceCost() == null ||
                        maintenance.getServiceCost() == request.getServiceCost())
                .filter(maintenance -> request.getRemarks() == null ||
                        Objects.equals(maintenance.getRemarks(), request.getRemarks()))
                .filter(maintenance -> request.getNextServiceDate() == null ||
                        Objects.equals(maintenance.getNextServiceDate(), request.getNextServiceDate()))
                .filter(maintenance -> request.getLastServiceDate() == null ||
                        Objects.equals(maintenance.getLastServiceDate(),request.getLastServiceDate()))
                .filter(maintenance -> request.getVehicleStatus() == null ||
                        maintenance.getVehicleStatus() == request.getVehicleStatus())
                .filter(maintenance -> request.getVehicleNumber() == null ||
                        java.util.Objects.equals(maintenance.getVehicleNumber(),request.getVehicleNumber()))
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<MaintenanceResponse> findByVehicleNumber(String vehicleNumber){
        List<Maintenance> maintenances = maintenanceRepository.findByVehicleNumber(vehicleNumber);
        return maintenances.stream().map(this::mapToResponse).toList();
    }

    private MaintenanceResponse mapToResponse(Maintenance maintenance) {
        MaintenanceResponse response = new MaintenanceResponse();
        response.setMaintenanceId(maintenance.getId());
        response.setServiceType(maintenance.getServiceType());
        response.setServiceCost(maintenance.getServiceCost());
        response.setRemarks(maintenance.getRemarks());
        response.setLastServiceDate(maintenance.getLastServiceDate());
        response.setNextServiceDate(maintenance.getNextServiceDate());
        response.setVehicleStatus(maintenance.getVehicleStatus());
        response.setVehicleId(maintenance.getVehicleId());
        response.setVehicleNumber(maintenance.getVehicleNumber());
        return response;

    }
}
