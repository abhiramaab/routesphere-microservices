package com.routesphere.invoice_service.repository;

import com.routesphere.invoice_service.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findByShipmentId(Long shipmentId);
}
