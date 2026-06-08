package com.vivaeventos.event_service.domain.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.vivaeventos.event_service.domain.dto.CreateEventRequest;
import com.vivaeventos.event_service.domain.dto.CreateTicketTypeRequest;
import com.vivaeventos.event_service.domain.dto.EventDetailResponse;
import com.vivaeventos.event_service.domain.dto.EventResponse;
import com.vivaeventos.event_service.domain.dto.TicketTypeResponse;
import com.vivaeventos.event_service.domain.exeption.BusinessException;
import com.vivaeventos.event_service.domain.exeption.ResourceNotFoundException;
import com.vivaeventos.event_service.domain.mapper.EventMapper;
import com.vivaeventos.event_service.domain.model.Event;
import com.vivaeventos.event_service.domain.model.EventStatus;
import com.vivaeventos.event_service.domain.model.TicketType;
import com.vivaeventos.event_service.domain.repository.IEventRepository;
import com.vivaeventos.event_service.domain.repository.ITicketTypeRepository;

import lombok.Builder;
/**
 * EventServiceImpl
 */
@Service
public class EventServiceImpl implements IEventService {
    
    private final IEventRepository eventRepository;
    private final ITicketTypeRepository ticketTypeRepository;

    public EventServiceImpl(IEventRepository eventRepository,
                            ITicketTypeRepository ticketTypeRepository) {
        this.eventRepository = eventRepository;
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @Override
    public EventResponse createEvent(CreateEventRequest request) {
        Event event = Event.builder()
                .name(request.getName())
                .description(request.getDescription())
                .date(request.getDate())
                .address(request.getAddress())
                .city(request.getCity())
                .status(EventStatus.DRAFT)
                .createdAt(LocalDateTime.now())
                .build();

        Event savedEvent = eventRepository.save(event);
    
        return EventMapper.toResponse(savedEvent);
    }

    // public void delete(Event event) { TODO eliminar o desactiva evento
    //     eventRepository.delete(event);
    // }

    @Override
    public EventDetailResponse findEventById(UUID id) {

        Event event = eventRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("Evento no encontrado"));

        if (event.getStatus() != EventStatus.ACTIVE) {

            throw new BusinessException(
                    "El evento no está disponible");
        }

        return EventMapper.toDetailResponse(event);
    }

    @Override
    public TicketTypeResponse createTicketType(
            UUID eventId,
            CreateTicketTypeRequest request) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Evento no encontrado"));

        TicketType ticketType = TicketType.builder()
                .name(request.getName())
                .price(request.getPrice())
                .maxCupo(request.getMaxCupo())
                .event(event)
                .build();

        TicketType savedTicketType =
                ticketTypeRepository.save(ticketType);

        return EventMapper.toResponse(savedTicketType);
    }

    @Override
    public EventResponse activateEvent(UUID eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Evento no encontrado"));

        if (event.getStatus() != EventStatus.DRAFT) {

            throw new BusinessException(
                    "Solo eventos en estado DRAFT pueden activarse");
        }

        boolean hasTicketTypes =
                ticketTypeRepository.existsByEvent_Id(eventId);

        if (!hasTicketTypes) {

            throw new BusinessException(
                    "El evento debe tener al menos un tipo de boleta");
        }

        event.setStatus(EventStatus.ACTIVE);

        Event updatedEvent =
                eventRepository.save(event);

        return EventMapper.toResponse(updatedEvent);
    }

    @Override
    public List<EventResponse> getActiveEvents() {

        return eventRepository
                .findByStatus(EventStatus.ACTIVE)
                .stream()
                .map(EventMapper::toResponse)
                .toList();
    }


    // @Override TODO
    // public Event update(Event event) {
    //     return eventRepository.save(event);
    // }

    // @Override TODO
    // public List<Event> findAll() {
    //     return eventRepository.findAll();
    // }

    // @Override TODO
    // public Page<Event> findAll(Pageable pageable) {
    //     return eventRepository.findAll(pageable);
    // }
    
}
