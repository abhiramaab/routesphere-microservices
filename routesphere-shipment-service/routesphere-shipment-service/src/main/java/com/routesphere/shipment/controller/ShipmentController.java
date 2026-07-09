package com.routesphere.shipment.controller;

import com.routesphere.shipment.dto.request.CreateShipmentRequest;
import com.routesphere.shipment.dto.request.SearchRequest;
import com.routesphere.shipment.dto.response.ShipmentResponse;
import com.routesphere.shipment.service.ShipmentService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment")
@Data
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    public ShipmentResponse createShipment(@RequestBody CreateShipmentRequest request){
        return shipmentService.createShipment(request);
    }

    @GetMapping("/{shipmentId}")
    public ShipmentResponse getShipmentById(@PathVariable Long shipmentId){
        return shipmentService.getShipmentById(shipmentId);
    }

    @DeleteMapping("{shipmentId}")
    public String deleteShipment(@PathVariable Long shipmentId){
        return shipmentService.deleteShipment(shipmentId);
    }

    @PutMapping("/{shipmentId}")
    public ShipmentResponse updateShipment(@PathVariable Long shipmentId, @RequestBody CreateShipmentRequest request){
        return shipmentService.updateShipment(shipmentId, request);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<ShipmentResponse> findByVehicleId(@PathVariable Long vehicleId){
        return shipmentService.findByVehicleId(vehicleId);
    }

    @PostMapping("/search")
    public List<ShipmentResponse> findBySearch(@RequestBody SearchRequest request){
        return shipmentService.findBySearch(request);
    }

    @GetMapping("/customer/{customerId}")
    public List<ShipmentResponse> findByCustomerId(@PathVariable Long customerId){
        return shipmentService.findByCustomerId(customerId);
    }

    @GetMapping
    public Page<ShipmentResponse> findAll(@PageableDefault(sort = "id", size = 10, page = 0, direction = Sort.Direction.ASC) Pageable pageable){
        return shipmentService.findAll(pageable);
    }
}
