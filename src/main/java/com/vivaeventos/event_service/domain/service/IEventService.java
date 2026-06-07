package com.vivaeventos.event_service.domain.service;

import com.vivaeventos.event_service.domain.dto.CreateEventRequest;
import com.vivaeventos.event_service.domain.dto.CreateTicketTypeRequest;
import com.vivaeventos.event_service.domain.dto.EventDetailResponse;
import com.vivaeventos.event_service.domain.dto.TicketTypeResponse;
import com.vivaeventos.event_service.domain.dto.EventResponse;
import com.vivaeventos.event_service.domain.model.Event;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEventService {
    EventResponse createEvent(CreateEventRequest request);

    TicketTypeResponse createTicketType(UUID eventId, CreateTicketTypeRequest request);

    EventResponse activateEvent(UUID eventId);

    List<EventResponse> getActiveEvents();

    EventDetailResponse findEventById(UUID id);

    EventResponse update(CreateEventRequest event);

    //Page<Event> findAll(Pageable pageable);
    //List<Event> findAll();
}
