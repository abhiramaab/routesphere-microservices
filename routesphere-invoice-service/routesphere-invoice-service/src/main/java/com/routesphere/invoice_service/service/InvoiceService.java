package com.routesphere.invoice_service.service;

import com.routesphere.invoice_service.dto.request.CreateInvoiceRequest;
import com.routesphere.invoice_service.dto.request.SearchRequest;
import com.routesphere.invoice_service.dto.response.InvoiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvoiceService {

    InvoiceResponse createInvoice(CreateInvoiceRequest request);
    InvoiceResponse getInvoice(Long id);
    InvoiceResponse updateInvoice(Long id, CreateInvoiceRequest request);
    String deleteInvoice(Long id);
    Page<InvoiceResponse> findAllInvoices(Pageable pageable);
    InvoiceResponse findByShipmentId(Long shipmentId);
    List<InvoiceResponse> searchInvoice(SearchRequest request);
    Page<InvoiceResponse> findAll(Pageable pageable);
}
