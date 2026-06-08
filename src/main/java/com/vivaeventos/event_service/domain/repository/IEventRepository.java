package com.vivaeventos.event_service.domain.repository;

import com.vivaeventos.event_service.domain.model.Event;
import com.vivaeventos.event_service.domain.model.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import java.util.List;

public interface IEventRepository extends JpaRepository<Event, UUID> {
    List<Event> findByStatus(EventStatus status);
}
