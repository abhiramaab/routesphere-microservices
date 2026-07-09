package com.routesphere.customer.dto.request;

import com.routesphere.customer.dto.response.ShipmentResponse;
import lombok.Data;

@Data
public class SearchRequest {

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
