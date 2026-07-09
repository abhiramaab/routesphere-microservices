package com.routesphere.trip_service.exception;

public class BadRequestException extends org.apache.coyote.BadRequestException {
    public BadRequestException(String message) {
        super(message);
    }
}
