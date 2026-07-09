package com.routesphere.shipment.dto.response;

import lombok.Data;

@Data
public class CustomerResponse {

    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;

}
