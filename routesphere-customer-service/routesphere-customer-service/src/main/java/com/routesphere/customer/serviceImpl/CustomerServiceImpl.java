package com.routesphere.customer.serviceImpl;

import com.routesphere.customer.client.ShipmentClient;
import com.routesphere.customer.dto.request.CreateCustomerRequest;
import com.routesphere.customer.dto.request.SearchRequest;
import com.routesphere.customer.dto.response.CustomerResponse;
import com.routesphere.customer.dto.response.ShipmentResponse;
import com.routesphere.customer.entity.Customer;
import com.routesphere.customer.repository.CustomerRepository;
import com.routesphere.customer.service.CustomerService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Data
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ShipmentClient shipmentClient;

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest request) {

        Customer customer = new Customer();
        customer.setGst(request.getGst());
        customer.setCompanyName(request.getCompanyName());
        customer.setAddress(request.getAddress());
        customer.setCity(request.getCity());
        customer.setState(request.getState());
        customer.setCountry(request.getCountry());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        Customer savedCustomer = customerRepository.save(customer);
        return mapToResponse(savedCustomer);
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CreateCustomerRequest request) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
        customer.setCompanyName(request.getCompanyName());
        customer.setGst(request.getGst());
        customer.setAddress(request.getAddress());
        customer.setCity(request.getCity());
        customer.setState(request.getState());
        customer.setCountry(request.getCountry());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        Customer updatedCustomer = customerRepository.save(customer);
        return mapToResponse(updatedCustomer);
    }

    @Override
    public String deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
        customerRepository.delete(customer);
        return "Customer with id " + id + " has been deleted";
    }

    @Override
    public CustomerResponse findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
        return mapToResponse(customer);
    }

    @Override
    public CustomerResponse findByShipmentId(Long shipmentId){
        Customer customer = customerRepository.findByShipmentId(shipmentId).orElseThrow(() -> new RuntimeException("Customer with id " + shipmentId + " not found"));
        return mapToResponse(customer);
    }



    private CustomerResponse mapToResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setGst(customer.getGst());
        customerResponse.setCompanyName(customer.getCompanyName());
        customerResponse.setCity(customer.getCity());
        customerResponse.setState(customer.getState());
        customerResponse.setCountry(customer.getCountry());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setPhoneNumber(customer.getPhoneNumber());
        customerResponse.setAddress(customer.getAddress());

        if (customer.getShipmentId() != null) {
            ShipmentResponse shipment = shipmentClient.findByShipmentId(customer.getShipmentId());
            customerResponse.setShipmentId(shipment);
        }

        return customerResponse;
    }

    @Override
    public CustomerResponse findByGst(String gst){
        Customer customer = customerRepository.findByGst(gst).orElseThrow(() -> new RuntimeException("Customer with GST: " + gst + " not found"));
        return mapToResponse(customer);
    }

    @Override
    public List<CustomerResponse> searchCustomer(SearchRequest request) {
        return customerRepository.findAll()
                .stream()
                .filter(customer -> request.getCompanyName() == null ||
                        Objects.equals(customer.getCompanyName(), request.getCompanyName()))
                .filter(customer -> request.getAddress() == null ||
                        Objects.equals(customer.getAddress(), request.getAddress()))
                .filter(customer -> request.getGst() == null ||
                        Objects.equals(customer.getGst(), request.getGst()))
                .filter(customer -> request.getCity() == null ||
                        Objects.equals(customer.getCity(), request.getCity()))
                .filter(customer -> request.getCountry() == null ||
                        Objects.equals(customer.getCountry(), request.getCountry()))
                .filter(customer -> request.getShipmentId() == null ||
                        Objects.equals(customer.getShipmentId(), request.getShipmentId()))
                .filter(customer -> request.getState() == null ||
                        Objects.equals(customer.getState(), request.getState()))
                .filter(customer -> request.getPhoneNumber() == null ||
                        Objects.equals(customer.getPhoneNumber(), request.getPhoneNumber()))
                .filter(customer -> request.getId() == null ||
                        Objects.equals(customer.getId(), request.getId()))
                .map(this::mapToResponse)
                .toList();
    }


}
