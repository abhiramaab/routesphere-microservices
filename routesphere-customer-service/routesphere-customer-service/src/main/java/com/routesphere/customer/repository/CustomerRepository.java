package com.routesphere.customer.repository;

import com.routesphere.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByGst(String gst);

    Optional<Customer> findByShipmentId(Long shipmentId);
}
