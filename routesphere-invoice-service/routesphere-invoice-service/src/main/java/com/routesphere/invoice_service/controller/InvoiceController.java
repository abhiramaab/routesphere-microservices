package com.routesphere.invoice_service.controller;

import com.routesphere.invoice_service.dto.request.CreateInvoiceRequest;
import com.routesphere.invoice_service.dto.request.SearchRequest;
import com.routesphere.invoice_service.dto.response.InvoiceResponse;
import com.routesphere.invoice_service.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
@Tag(name = "Invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    @Operation(summary = "Create a invoice")
    public InvoiceResponse createInvoice(@RequestBody CreateInvoiceRequest request){
        return invoiceService.createInvoice(request);
    }

    @DeleteMapping("/{invoiceId}")
    @Operation(summary = "Delete a invoice by Id")
    public String deleteInvoice(@PathVariable("invoiceId") Long invoiceId){
        return invoiceService.deleteInvoice(invoiceId);
    }

    @PutMapping("/{invoiceId}")
    @Operation(summary = "Update a invoice by Id")
    public InvoiceResponse updateInvoice(@PathVariable Long invoiceId, @RequestBody CreateInvoiceRequest request){
        return invoiceService.updateInvoice(invoiceId, request);
    }

    @PostMapping("/search")
    @Operation(summary = "Search invoice using on or more criteria")
    public List<InvoiceResponse> searchInvoice(@RequestBody SearchRequest request){
        return invoiceService.searchInvoice(request);
    }

    @GetMapping
    @Operation(summary = "Retrieve all invoices")
    public Page<InvoiceResponse> findAll(@PageableDefault(sort = "id", size = 10, direction = Sort.Direction.ASC, page = 0) Pageable pageable){
        return invoiceService.findAll(pageable);
    }

    @GetMapping("/{invoiceId}")
    @Operation(summary = "Retrieve invoice by Id")
    public InvoiceResponse findByInvoiceId(@PathVariable Long invoiceId){
        return invoiceService.getInvoice(invoiceId);
    }
}
