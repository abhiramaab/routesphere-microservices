package com.routesphere.fuellog_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FuellogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuellogServiceApplication.class, args);
	}

}
