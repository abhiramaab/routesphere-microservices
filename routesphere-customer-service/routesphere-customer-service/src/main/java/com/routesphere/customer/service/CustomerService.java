package com.routesphere.customer.service;

import com.routesphere.customer.dto.request.CreateCustomerRequest;
import com.routesphere.customer.dto.request.SearchRequest;
import com.routesphere.customer.dto.response.CustomerResponse;
import com.routesphere.customer.dto.response.ShipmentResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CreateCustomerRequest request);
    CustomerResponse updateCustomer(Long id, CreateCustomerRequest request);
    String deleteCustomer(Long id);
    CustomerResponse findById(Long id);

    CustomerResponse findByShipmentId(Long shipmentId);

    CustomerResponse findByGst(String gst);

    List<CustomerResponse> searchCustomer(SearchRequest request);
}
