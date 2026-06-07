package com.vivaeventos.event_service.domain.model;

import lombok.*;
import lombok.Builder;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Table(name = "events")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @NotEmpty(message ="No puede estar vacio")
    @Column(nullable=false)
    private EventStatus status;

    @Builder.Default
    @OneToMany(
        mappedBy = "event", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true,
        fetch = FetchType.EAGER)
    private List<TicketType> ticketTypes = new ArrayList<>();
}
