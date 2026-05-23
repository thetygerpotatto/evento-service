package com.vivaeventos.event_service.domain.repository;

import java.util.UUID;
import com.vivaeventos.event_service.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventRepository extends JpaRepository<Event, UUID> {}

