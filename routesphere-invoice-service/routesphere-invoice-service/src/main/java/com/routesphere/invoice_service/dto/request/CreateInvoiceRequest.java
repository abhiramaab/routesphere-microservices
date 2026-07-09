package com.routesphere.invoice_service.dto.request;

import com.routesphere.invoice_service.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateInvoiceRequest {


    @NotBlank(message = "Invoice number cannot be empty")
    private String invoiceNumber;

    @NotBlank(message = "Customer name cannot be empty")
    private String customerName;

    @NotNull(message = "GST amount cannot be empty")
    private BigDecimal gstAmount;

    @NotNull(message = "Payment status cannot be empty")
    private PaymentStatus paymentStatus;
}
