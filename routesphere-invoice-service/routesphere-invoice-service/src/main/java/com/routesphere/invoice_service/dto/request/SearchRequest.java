package com.routesphere.invoice_service.dto.request;

import com.routesphere.invoice_service.dto.response.ShipmentResponse;
import com.routesphere.invoice_service.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SearchRequest {

    private Long id;

    private String invoiceNumber;
    private LocalDate invoiceDate;
    private String customerName;
    private BigDecimal gstAmount;

    private PaymentStatus paymentStatus;
    private ShipmentResponse shipmentId;
}
