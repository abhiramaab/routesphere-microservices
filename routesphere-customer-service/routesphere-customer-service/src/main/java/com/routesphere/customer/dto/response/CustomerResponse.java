package com.routesphere.customer.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CustomerResponse {

    private Long Id;

    private String companyName;
    private String email;
    private String phoneNumber;
    private String address;
    private String gst;
    private String city;
    private String state;
    private String country;

    private ShipmentResponse shipmentId;
}
