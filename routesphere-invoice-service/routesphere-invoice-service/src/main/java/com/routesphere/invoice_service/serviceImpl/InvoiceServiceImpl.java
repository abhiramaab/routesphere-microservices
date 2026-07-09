package com.routesphere.invoice_service.serviceImpl;

import com.routesphere.invoice_service.client.ShipmentClient;
import com.routesphere.invoice_service.dto.request.CreateInvoiceRequest;
import com.routesphere.invoice_service.dto.request.SearchRequest;
import com.routesphere.invoice_service.dto.response.InvoiceResponse;
import com.routesphere.invoice_service.dto.response.ShipmentResponse;
import com.routesphere.invoice_service.entity.Invoice;
import com.routesphere.invoice_service.repository.InvoiceRepository;
import com.routesphere.invoice_service.service.InvoiceService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ShipmentClient shipmentClient;

    @Override
    public InvoiceResponse createInvoice(CreateInvoiceRequest request) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(request.getInvoiceNumber());
        invoice.setCustomerName(request.getCustomerName());
        invoice.setGstAmount(request.getGstAmount());
        invoice.setPaymentStatus(request.getPaymentStatus());

        Invoice savedInvoice = invoiceRepository.save(invoice);
        return mapToResponse(savedInvoice);
    }

    @Override
    public InvoiceResponse getInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new RuntimeException("invoice not found"));
        return mapToResponse(invoice);
    }

    @Override
    public InvoiceResponse updateInvoice(Long id, CreateInvoiceRequest request) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("invoice not found"));
        invoice.setInvoiceNumber(request.getInvoiceNumber());
        invoice.setCustomerName(request.getCustomerName());
        invoice.setGstAmount(request.getGstAmount());
        invoice.setPaymentStatus(request.getPaymentStatus());
        Invoice updatedInvoice = invoiceRepository.save(invoice);
        return mapToResponse(updatedInvoice);
    }

    @Override
    public String deleteInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("invoice not found"));
        invoiceRepository.delete(invoice);
        return "invoice deleted : " + invoice.getInvoiceNumber();
    }

    @Override
    public Page<InvoiceResponse> findAllInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable).map(this::mapToResponse);
    }

    @Override
    public InvoiceResponse findByShipmentId(Long shipmentId) {
        Invoice invoice = invoiceRepository.findByShipmentId(shipmentId).orElseThrow(() -> new RuntimeException("invoice not found"));
        return mapToResponse(invoice);
    }

    @Override
    public List<InvoiceResponse> searchInvoice(SearchRequest request) {
        return invoiceRepository.findAll().stream()
                .filter(invoice -> request.getInvoiceDate() == null ||
                        Objects.equals(invoice.getInvoiceDate(), request.getInvoiceDate()))
                .filter(invoice -> request.getShipmentId() == null ||
                        Objects.equals(invoice.getShipmentId(), request.getShipmentId()))
                .filter(invoice -> request.getInvoiceNumber() == null ||
                        Objects.equals(invoice.getInvoiceNumber(), request.getInvoiceNumber()))
                .filter(invoice -> request.getGstAmount() == null ||
                        invoice.getGstAmount().compareTo(request.getGstAmount()) == 0)
                .filter(invoice -> request.getPaymentStatus() == null ||
                        invoice.getPaymentStatus() == request.getPaymentStatus())
                .filter(invoice -> request.getCustomerName() == null ||
                        Objects.equals(invoice.getCustomerName(), request.getCustomerName()))
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public Page<InvoiceResponse> findAll(Pageable pageable) {
        return invoiceRepository.findAll(pageable).map(this::mapToResponse);
    }

    private InvoiceResponse mapToResponse(Invoice invoice) {
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setId(invoice.getId());
        invoiceResponse.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceResponse.setInvoiceDate(invoice.getInvoiceDate());
        invoiceResponse.setCustomerName(invoice.getCustomerName());
        invoiceResponse.setGstAmount(invoice.getGstAmount());
        invoiceResponse.setPaymentStatus(invoice.getPaymentStatus());

        if (invoice.getShipmentId() != null) {
            ShipmentResponse shipment = shipmentClient.findByShipmentId(invoice.getShipmentId());
            invoiceResponse.setShipmentId(shipment);
        }
        return invoiceResponse;
    }
}
