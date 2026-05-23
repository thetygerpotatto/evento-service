package com.vivaeventos.event_service.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eventId;

    @NotEmpty(message ="No puede estar vacio")
    @Size(min=2, max=20, message="El tamaño tiene que estar entre 2 y 20")
    @Column(nullable=false)
    private String name;

    @NotEmpty(message ="No puede estar vacio")
    @Size(min=2, max=20, message="El tamaño tiene que estar entre 2 y 20")
    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private LocalDateTime date;

    @NotEmpty(message ="No puede estar vacio")
    @Size(min=2, max=20, message="El tamaño tiene que estar entre 2 y 20")
    @Column(nullable=false)
    private String address;

    @Column(nullable=false)
    private Integer general_capacity;
    @Column(nullable=false)
    private Integer vip_capacity;
    @Column(nullable=false)
    private Integer student_capacity;
    
    @Column(nullable=false)
    private Integer general_tickets_sold;
    @Column(nullable=false)
    private Integer vip_tickets_sold;
    @Column(nullable=false)
    private Integer student_tickets_sold;

    @Column(nullable=false)
    private Double general_price;
    @Column(nullable=false)
    private Double vip_price;
    @Column(nullable=false)
    private Double student_price;
}
