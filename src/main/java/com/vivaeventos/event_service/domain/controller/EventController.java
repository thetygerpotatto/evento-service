package com.vivaeventos.event_service.domain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vivaeventos.event_service.domain.service.IEventService;
import com.vivaeventos.event_service.domain.model.Event;

import jakarta.validation.Valid;


/**
 * EventController
 */
@RestController
@RequestMapping("/eventos-api")
public class EventController {

    private final IEventService eventService;
    
    private final String EVENTS = "events";
    private final String MSJ = "mensaje";
    private final String EVENT = "event";


    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    
    /**
     * Listar todos los eventos
     */
    @GetMapping("/eventos")
    public ResponseEntity<Map<String, Object>> getEventos() {
        List<Event> events = eventService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put(EVENTS, events);
        return ResponseEntity.ok(response);
    }

    /**
     * Listar eventos con paginación.
     */
    @GetMapping("/eventos/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Event> productos = eventService.findAll(pageable);
        return ResponseEntity.ok(productos);
    }

    /**
     * Crear un nuevo producto pasando el objeto en el cuerpo de la petición, usando validaciones
     */
    @PostMapping("/eventos")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Event event, BindingResult result) {
        if (result.hasErrors()) {
            ResponseEntity.badRequest();
        }
        Map<String, Object> response = new HashMap<>();
        Event newEvent = eventService.save(event);
        response.put(MSJ, "El evento ha sido creado con éxito!");
        response.put(EVENT, newEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    /**
     * Eliminar un evento pasando el objeto en el cuerpo de la petición.
     */
    @DeleteMapping("/eventos")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Event event) {
        eventService.findById(event.getEventId);
        eventService.delete(event);
        Map<String, Object> response = new HashMap<>();
        response.put(MSJ, "El producto ha sido eliminado con éxito!");
        response.put(EVENT, null);
        return ResponseEntity.ok(response);
    }

    /**
     * Actualizar un producto pasando el objeto en el cuerpo de la petición.
     * @param evento: Objeto Producto que se va a actualizar
     */
    @PutMapping("/eventos")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Event event, BindingResult result) {
        eventService.findById(event.getEventId());
        Map<String, Object> response = new HashMap<>();
        Event updatedEvent = eventService.update(event);
        response.put(MSJ, "El producto ha sido actualizado con éxito!");
        response.put(EVENT, updatedEvent);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtener un producto por su ID.
     */
    @GetMapping("/eventos/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable UUID id) {
    Optional<Event> event = eventService.findById(id);
    Map<String, Object> response = new HashMap<>();
        response.put(MSJ, "El evento ha sido encontrado con éxito!");
        response.put(EVENT, event);
        return ResponseEntity.ok(response);
    }
}
