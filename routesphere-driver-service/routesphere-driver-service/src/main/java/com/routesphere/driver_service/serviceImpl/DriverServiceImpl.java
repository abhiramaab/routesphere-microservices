package com.routesphere.driver_service.serviceImpl;

import com.routesphere.driver_service.dto.request.CreateDriverRequest;
import com.routesphere.driver_service.dto.request.SearchRequest;
import com.routesphere.driver_service.dto.response.DriverResponse;
import com.routesphere.driver_service.entity.Driver;
import com.routesphere.driver_service.repository.DriverRepository;
import com.routesphere.driver_service.service.DriverService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public DriverResponse createDriver(CreateDriverRequest request) {
        Driver driver = new Driver();
        driver.setDriverName(request.getDriverName());
        driver.setDriverPhone(request.getDriverPhone());
        driver.setDriverStatus(request.getDriverStatus());
        driver.setDriverLicenseExpiry(request.getDriverLicenseExpiry());
        driver.setDriverLicenseNumber(request.getDriverLicenseNumber());
        driver.setExperienceYears(request.getExperienceYears());
        Driver savedDriver  = driverRepository.save(driver);

        return mapToDriverResponse(savedDriver);
    }

    @Override
    public DriverResponse findDriverById(Long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver Not Found"));
        return mapToDriverResponse(driver);
    }

    @Override
    public String deleteDriverById(Long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver Not Found"));
        driverRepository.delete(driver);
        return "Driver Deleted Successfully : " + driver.getDriverName();
    }

    @Override
    public DriverResponse updateDriver(Long id, CreateDriverRequest request) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver Not Found"));
        driver.setDriverName(request.getDriverName());
        driver.setDriverPhone(request.getDriverPhone());
        driver.setDriverStatus(request.getDriverStatus());
        driver.setDriverLicenseExpiry(request.getDriverLicenseExpiry());
        driver.setDriverLicenseNumber(request.getDriverLicenseNumber());
        driver.setExperienceYears(request.getExperienceYears());
        Driver savedDriver  = driverRepository.save(driver);
        return mapToDriverResponse(savedDriver);
    }

    @Override
    public Page<DriverResponse> findAllDrivers(Pageable pageable) {
        return driverRepository.findAll(pageable).map(this::mapToDriverResponse);
    }

    @Override
    public DriverResponse findDriverByDriverName(String driverName) {
        Driver driver = driverRepository.findDriverByDriverName(driverName).orElseThrow(() -> new RuntimeException("Driver Not Found"));
        return mapToDriverResponse(driver);
    }

    @Override
    public List<DriverResponse> searchDriver(SearchRequest request) {
        return driverRepository.findAll().stream()
                .filter(driver -> request.getDriverName() == null ||
                        driver.getDriverName().toLowerCase().contains(request.getDriverName().toLowerCase()))
                .filter(driver -> request.getDriverPhone() == null ||
                        driver.getDriverPhone().equalsIgnoreCase(request.getDriverPhone()))
                .filter(driver -> request.getDriverLicenseNumber() == null ||
                        driver.getDriverLicenseNumber().equalsIgnoreCase(request.getDriverLicenseNumber()))
                .filter(driver -> request.getDriverStatus() == null ||
                        driver.getDriverStatus() == request.getDriverStatus())
                .filter(driver -> request.getExperienceYears() == null ||
                        driver.getExperienceYears() == request.getExperienceYears())
                .filter(driver -> request.getDriverLicenseExpiry() == null ||
                        driver.getDriverLicenseExpiry() == request.getDriverLicenseExpiry())
                .filter(driver -> request.getMaxExperience() == null ||
                        driver.getExperienceYears() <= request.getMaxExperience())
                .filter(driver -> request.getMinExperience() == null ||
                        driver.getExperienceYears() >= request.getMinExperience())
                .map(this::mapToDriverResponse)
                .toList();
    }

    private DriverResponse mapToDriverResponse(Driver driver) {
        DriverResponse driverResponse = new DriverResponse();
        driverResponse.setDriverId(driver.getId());
        driverResponse.setDriverName(driver.getDriverName());
        driverResponse.setDriverPhone(driver.getDriverPhone());
        driverResponse.setDriverStatus(driver.getDriverStatus());
        driverResponse.setDriverLicenseExpiry(driver.getDriverLicenseExpiry());
        driverResponse.setDriverLicenseNumber(driver.getDriverLicenseNumber());
        driverResponse.setExperienceYears(driver.getExperienceYears());
        return driverResponse;

    }
}
