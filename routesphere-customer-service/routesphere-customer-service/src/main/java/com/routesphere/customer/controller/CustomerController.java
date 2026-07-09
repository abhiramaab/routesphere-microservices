package com.routesphere.customer.controller;

import com.routesphere.customer.dto.request.CreateCustomerRequest;
import com.routesphere.customer.dto.request.SearchRequest;
import com.routesphere.customer.dto.response.CustomerResponse;
import com.routesphere.customer.service.CustomerService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@Data
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping
    public CustomerResponse createCustomer(@RequestBody CreateCustomerRequest request){
        return customerService.createCustomer(request);
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id){
        return customerService.findById(id);
    }



    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody CreateCustomerRequest request){
        return customerService.updateCustomer(id, request);
    }

    @DeleteMapping("/{id}")
    public String  deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }

    @GetMapping("/gst/{gst}")
    public CustomerResponse findByGst(@PathVariable String gst){
        return customerService.findByGst(gst);
    }

    @GetMapping("/shipment/{shipmentId}")
    public CustomerResponse findByShipmentId(@PathVariable Long shipmentId){
        return customerService.findByShipmentId(shipmentId);
    }

    @PostMapping("/search")
    public List<CustomerResponse> searchCustomer(@RequestBody SearchRequest request){
        return customerService.searchCustomer(request);
    }
}
