package com.vivaeventos.event_service.domain.mapper;

import com.vivaeventos.event_service.domain.dto.EventDetailResponse;
import com.vivaeventos.event_service.domain.dto.EventResponse;
import com.vivaeventos.event_service.domain.dto.TicketTypeResponse;
import com.vivaeventos.event_service.domain.model.Event;
import com.vivaeventos.event_service.domain.model.TicketType;

public class EventMapper {

    private EventMapper() {}

    public static EventResponse toResponse(Event event) {

        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .date(event.getDate())
                .address(event.getAddress())
                .status(event.getStatus())
                .createdBy(event.getCreatedBy())
                .createdAt(event.getCreatedAt())
                .activatedBy(event.getActivatedBy())
                .activatedAt(event.getActivatedAt())
                .build();
    }

    public static TicketTypeResponse toResponse(
            TicketType ticketType) {

        return TicketTypeResponse.builder()
                .id(ticketType.getId())
                .name(ticketType.getName())
                .precio(ticketType.getPrecio())
                .cupoMaximo(ticketType.getCupoMaximo())
                .build();
    }

    public static EventDetailResponse toDetailResponse(
            Event event) {

        return EventDetailResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .date(event.getDate())
                .address(event.getAddress())
                .status(event.getStatus())
                .ticketTypes(
                        event.getTicketTypes()
                                .stream()
                                .map(EventMapper::toResponse)
                                .toList()
                )
                .build();
    }
}
