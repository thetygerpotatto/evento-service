package com.vivaeventos.event_service.domain.exeption;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
