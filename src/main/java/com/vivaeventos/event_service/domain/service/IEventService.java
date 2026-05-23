package com.vivaeventos.event_service.domain.service;

import com.vivaeventos.event_service.domain.model.Event;
import java.util.List;
import java.util.Optional;

public interface IEventService {
    Event save(Event producto);
    void delete(Event producto);
    Optional<Event> findById(Long id);
    Event update(Event event);
    List<Event> findAll();
}
