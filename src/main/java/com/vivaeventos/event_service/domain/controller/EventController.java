package com.vivaeventos.event_service.domain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vivaeventos.event_service.domain.service.IEventService;
import com.vivaeventos.event_service.domain.dto.CreateEventRequest;
import com.vivaeventos.event_service.domain.dto.CreateTicketTypeRequest;
import com.vivaeventos.event_service.domain.dto.EventDetailResponse;
import com.vivaeventos.event_service.domain.dto.EventResponse;
import com.vivaeventos.event_service.domain.dto.TicketTypeResponse;
import com.vivaeventos.event_service.domain.model.Event;

import jakarta.validation.Valid;


/**
 * EventController
 */
@RestController
@RequestMapping("/events")
public class EventController {

    private final IEventService eventService;
    
    private final String EVENTS = "events";
    private final String MSJ = "mensaje";
    private final String EVENT = "event";


    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    
    /**
     * Crear un nuevo evento pasando el objeto en el cuerpo de la petición, usando validaciones
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createEvent(@Valid @RequestBody CreateEventRequest request,
            BindingResult result) {
        if (result.hasErrors()) {
            ResponseEntity.badRequest();
        }
        Map<String, Object> response = new HashMap<>();

        EventResponse event = eventService.createEvent(request);

        response.put(MSJ, "El evento ha sido creado con éxito!");
        response.put(EVENT, event);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{eventId}/ticket-types")
    public ResponseEntity<TicketTypeResponse> createTicketType(
            @PathVariable UUID eventId,
            @Valid @RequestBody CreateTicketTypeRequest request) {

        TicketTypeResponse ticketType =
                eventService.createTicketType(
                        eventId,
                        request
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ticketType);
    }

    @PostMapping("/{eventId}/activate")
    public ResponseEntity<EventResponse> activateEvent(
            @PathVariable UUID eventId) {

        EventResponse event =
                eventService.activateEvent(eventId);

        return ResponseEntity.ok(event);
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getActiveEvents() {
        return ResponseEntity.ok(
                eventService.getActiveEvents()
        );
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDetailResponse> getEventById(
            @PathVariable UUID eventId) {

        return ResponseEntity.ok(
                eventService.findEventById(eventId)
        );
    }
}
