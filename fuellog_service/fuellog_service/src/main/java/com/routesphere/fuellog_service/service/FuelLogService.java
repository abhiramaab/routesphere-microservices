package com.routesphere.fuellog_service.service;

import com.routesphere.fuellog_service.dto.request.CreateLogRequest;
import com.routesphere.fuellog_service.dto.LogResponse;
import com.routesphere.fuellog_service.dto.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FuelLogService {

    LogResponse createLog(CreateLogRequest request);
    LogResponse updateLog(Long id, CreateLogRequest request);
    String deleteLog(Long id);
    LogResponse getLog(Long id);
    Page<LogResponse> getAllLogs(Pageable pageable);

    List<LogResponse> findByVehicleId(Long vehicleId);
    List<LogResponse> findByShipmentId(Long shipmentId);
    List<LogResponse> findByTripId(Long tripId);
    List<LogResponse> searchLog(SearchRequest request);
    Page<LogResponse> findAll(Pageable pageable);
}
