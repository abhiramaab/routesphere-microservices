package com.routesphere.customer.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateCustomerRequest {

    private String companyName;
    private String email;
    private String gst;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String country;
}
