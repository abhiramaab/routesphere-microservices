package com.routesphere.invoice_service.entity;

import com.routesphere.invoice_service.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;

    @CreatedDate
    private LocalDate invoiceDate;
    private String customerName;
    private BigDecimal gstAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private Long shipmentId;
}
