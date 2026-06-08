package com.vivaeventos.event_service.domain.dto;

import com.vivaeventos.event_service.domain.model.EventStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class EventDetailResponse {

    private UUID id;

    private String name;

    private String description;

    private LocalDate date;

    private String address;

    private String city;

    private EventStatus status;

    private List<TicketTypeResponse> ticketTypes;
}
