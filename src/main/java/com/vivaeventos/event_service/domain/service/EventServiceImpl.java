package com.vivaeventos.event_service.domain.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import com.vivaeventos.event_service.domain.model.Event;
import com.vivaeventos.event_service.domain.repository.IEventRepository;
/**
 * EventServiceImpl
 */
@Service
public class EventServiceImpl implements IEventService {
    
    IEventRepository eventRepository;

    public EventServiceImpl(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void delete(Event event) {
        eventRepository.delete(event);
    }

    @Override
    public Optional<Event> findById(UUID id) {
        return eventRepository.findById(id);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }
    
}
