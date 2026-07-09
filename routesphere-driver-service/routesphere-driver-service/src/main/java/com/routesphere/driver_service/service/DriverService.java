package com.routesphere.driver_service.service;

import com.routesphere.driver_service.dto.request.CreateDriverRequest;
import com.routesphere.driver_service.dto.request.SearchRequest;
import com.routesphere.driver_service.dto.response.DriverResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DriverService {

    DriverResponse createDriver(CreateDriverRequest request);
    DriverResponse findDriverById(Long id);
    String deleteDriverById(Long id);
    DriverResponse updateDriver(Long id, CreateDriverRequest request);
    Page<DriverResponse> findAllDrivers(Pageable pageable);
    DriverResponse findDriverByDriverName(String driverName);
    List<DriverResponse> searchDriver(SearchRequest request);

}
