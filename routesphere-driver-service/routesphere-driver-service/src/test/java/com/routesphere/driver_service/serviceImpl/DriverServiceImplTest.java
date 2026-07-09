package com.routesphere.driver_service.serviceImpl;

import com.routesphere.driver_service.dto.request.CreateDriverRequest;
import com.routesphere.driver_service.dto.response.DriverResponse;
import com.routesphere.driver_service.entity.Driver;
import com.routesphere.driver_service.enums.DriverStatus;
import com.routesphere.driver_service.repository.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DriverServiceImplTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverServiceImpl driverServiceImpl;

    private Driver driver;
    private CreateDriverRequest request;

    @BeforeEach
    public void setUp() {
        request = new CreateDriverRequest();
        request.setDriverLicenseExpiry(LocalDate.parse("2027-09-21"));
        request.setDriverLicenseNumber("HJ267176261BN");
        request.setDriverName("Harish");
        request.setDriverStatus(DriverStatus.AVAILABLE);
        request.setExperienceYears(5.8);

        driver = new Driver();
        driver.setDriverLicenseExpiry(LocalDate.parse("2027-09-21"));
        driver.setDriverLicenseNumber("HJ267176261BN");
        driver.setDriverName("Harish");
        driver.setDriverStatus(DriverStatus.AVAILABLE);
        driver.setExperienceYears(5.8);
        driver.setId(1L);
    }

    @Test
    void createDriver() {
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);
        DriverResponse response = driverServiceImpl.createDriver(request);
        assertEquals(driver.getDriverName(), response.getDriverName());
        assertEquals(driver.getDriverPhone(), response.getDriverPhone());
        assertEquals(driver.getId(), response.getDriverId());
        assertEquals(driver.getDriverLicenseExpiry(), response.getDriverLicenseExpiry());
        assertEquals(driver.getDriverStatus(), response.getDriverStatus());
        assertEquals(driver.getExperienceYears(), response.getExperienceYears());
        assertEquals(driver.getDriverLicenseNumber(), response.getDriverLicenseNumber());
        verify(driverRepository).save(any(Driver.class));
    }
}
