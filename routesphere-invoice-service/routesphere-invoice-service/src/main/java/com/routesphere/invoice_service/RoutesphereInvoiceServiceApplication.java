package com.routesphere.invoice_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RoutesphereInvoiceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoutesphereInvoiceServiceApplication.class, args);
	}

}
