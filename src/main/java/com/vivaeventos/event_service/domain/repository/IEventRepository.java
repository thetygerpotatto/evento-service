package com.vivaeventos.event_service.domain.repository;

import com.vivaeventos.event_service.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IEventRepository extends JpaRepository<Event, UUID> {}
