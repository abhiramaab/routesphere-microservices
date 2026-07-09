package com.routesphere.maintenance_service.service;

import com.routesphere.maintenance_service.dto.request.CreateMaintenanceRequest;
import com.routesphere.maintenance_service.dto.request.SearchRequest;
import com.routesphere.maintenance_service.dto.response.MaintenanceResponse;
import com.routesphere.maintenance_service.entity.Maintenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MaintenanceService {

    MaintenanceResponse createMaintenance(CreateMaintenanceRequest request);
    MaintenanceResponse updateMaintenance(Long maintenanceId, CreateMaintenanceRequest request);
    String deleteMaintenanceById(Long maintenanceId);
    MaintenanceResponse findMaintenanceById(Long maintenanceId);
    Page<MaintenanceResponse> findAllMaintenance(Pageable pageable);

    List<MaintenanceResponse> findByVehicleId(Long vehicleId);
    List<MaintenanceResponse> searchMaintenance(SearchRequest request);

    List<MaintenanceResponse> findByVehicleNumber(String vehicleNumber);
}
