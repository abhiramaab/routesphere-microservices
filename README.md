# RouteSphere Microservices

## Overview

RouteSphere Microservices is a distributed logistics and fleet management system built using the Spring Boot microservices architecture. The project demonstrates service decomposition, centralized routing, service discovery, JWT-based authentication, Docker containerization, and RESTful communication between independent services.

This project is an evolution of the RouteSphere Monolithic application into a scalable microservices architecture.

---

## Architecture

```
                         Client
                            │
                            ▼
                  API Gateway (8080)
                            │
 ┌──────────────┬────────────┼─────────────┬─────────────┐
 │              │            │             │             │
 ▼              ▼            ▼             ▼             ▼

Auth       Vehicle      Driver      Customer     Shipment
8089         8082         8084         8090         8083

Invoice      Trip      Maintenance    FuelLog
8085         8086         8087         8088

            │
            ▼
   Eureka Discovery Server (8761)

            │
            ▼
         MySQL Database
```

---

## Microservices

| Service | Description | Port |
|----------|-------------|------|
| API Gateway | Central entry point for all client requests | 8080 |
| Eureka Discovery Server | Service registry and discovery | 8761 |
| Authentication Service | User authentication and JWT generation | 8089 |
| Vehicle Service | Vehicle management | 8082 |
| Driver Service | Driver management | 8084 |
| Customer Service | Customer management | 8090 |
| Shipment Service | Shipment management | 8083 |
| Invoice Service | Invoice generation | 8085 |
| Trip Service | Trip management | 8086 |
| Maintenance Service | Vehicle maintenance records | 8087 |
| FuelLog Service | Fuel tracking and history | 8088 |

---

## Features

- Spring Boot Microservices
- RESTful API Development
- Spring Cloud Gateway
- Eureka Service Discovery
- JWT Authentication
- Spring Security
- Spring Data JPA
- MySQL Integration
- Docker Containerization
- Docker Compose
- Swagger/OpenAPI Documentation
- Role-Based Authentication
- Centralized API Routing
- Independent Service Architecture

---

## Technology Stack

### Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka
- Spring Data JPA
- Hibernate

### Database

- MySQL

### Security

- JWT Authentication

### Build Tools

- Maven

### DevOps

- Docker
- Docker Compose
- Git
- GitHub

### Development Tools

- IntelliJ IDEA
- Postman

---

## Project Structure

```
routesphere-microservices

├── auth_service
├── fuellog_service
├── maintenance_service
├── rds
├── routesphere-api-gateway
├── routesphere-customer-service
├── routesphere-driver-service
├── routesphere-invoice-service
├── routesphere-shipment-service
├── routesphere-trip-service
└── routesphere-vehicle-service
```

---

## Authentication Flow

```
Client
   │
   ▼
API Gateway
   │
   ▼
Authentication Service
   │
Generate JWT Token
   │
   ▼
Gateway validates JWT
   │
   ▼
Requested Microservice
```

---

## API Routes

```
/api/auth/**
/api/vehicle/**
/api/driver/**
/api/customer/**
/api/shipment/**
/api/invoice/**
/api/trip/**
/api/maintenance/**
/api/log/**
```

---

## Running the Project

### Clone Repository

```bash
git clone https://github.com/abhiramaab/routesphere-microservices.git
```

### Build

```bash
mvn clean package
```

### Docker

```bash
docker compose up
```

---

## Screenshots

The following screenshots can be added to demonstrate the project:

- Eureka Dashboard
- API Gateway
- Swagger UI
- Docker Containers
- Postman API Testing
- MySQL Database

---

## Future Enhancements

- Spring Cloud Config Server
- Distributed Tracing
- Centralized Logging
- Redis Caching
- Kafka Integration
- Kubernetes Deployment
- CI/CD Pipeline
- Prometheus & Grafana Monitoring

---

## Related Project

**RouteSphere Monolithic Application**

The original monolithic implementation of RouteSphere is available as a separate repository and demonstrates the application's evolution into a microservices architecture.

Live demo - http://project.routesphere.abhiram.tech


---

