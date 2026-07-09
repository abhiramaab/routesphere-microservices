package com.routesphere.shipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class RoutesphereShipmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoutesphereShipmentServiceApplication.class, args);
	}

}
