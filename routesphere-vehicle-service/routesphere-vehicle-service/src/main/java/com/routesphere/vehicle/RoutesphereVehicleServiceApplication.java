package com.routesphere.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RoutesphereVehicleServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(RoutesphereVehicleServiceApplication.class, args);
	}

}
