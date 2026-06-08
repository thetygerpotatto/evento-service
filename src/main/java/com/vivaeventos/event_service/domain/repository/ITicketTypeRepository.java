package com.vivaeventos.event_service.domain.repository;

import com.vivaeventos.event_service.domain.model.TicketType;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ITicketTypeRepository extends JpaRepository<TicketType, UUID> {
    boolean existsByEvent_Id(UUID eventId);
}
