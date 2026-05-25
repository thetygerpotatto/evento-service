package com.vivaeventos.event_service.domain.service;

import com.vivaeventos.event_service.domain.model.Event;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEventService {
    Event save(Event event);
    void delete(Event event);
    Optional<Event> findById(UUID id);
    Event update(Event event);
    Page<Event> findAll(Pageable pageable);
    List<Event> findAll();
}
