package com.vivaeventos.event_service.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eventId;

    private String name;
    private String description;
    private LocalDateTime date;
    private String Address;
    private Integer general_capacity;
    private Integer vip_capacity;
    private Integer student_capacity;
    
}
