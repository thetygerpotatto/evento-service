package com.vivaeventos.event_service.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketTypeResponse {

    private UUID id;

    private String name;

    private BigDecimal price;

    private Integer maxCup;
}
