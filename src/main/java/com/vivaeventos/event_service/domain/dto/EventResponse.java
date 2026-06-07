package com.vivaeventos.event_service.domain.dto;

import com.vivaeventos.event_service.domain.model.EventStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class EventResponse {

    private Long id;

    private String nombre;

    private String descripcion;

    private LocalDate fecha;

    private String lugar;

    private String ciudad;

    private EventStatus estado;

    private Long createdBy;

    private LocalDateTime createdAt;

    private Long activatedBy;

    private LocalDateTime activatedAt;
}
