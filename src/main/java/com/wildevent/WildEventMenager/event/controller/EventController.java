package com.wildevent.WildEventMenager.event.controller;

import com.wildevent.WildEventMenager.event.model.dto.EventDTO;
import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDateDTO;
import com.wildevent.WildEventMenager.event.service.EventServiceImpl;
import com.wildevent.WildEventMenager.security.AccessUrlProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    private final static String EVENT_URL = "/event";
    private final static String NO_AUTH_EVENT_URL = AccessUrlProvider.NO_AUTH + EVENT_URL;
    private final static String EVENT_MANAGEMENT_EVENT_URL = AccessUrlProvider.EVENT_MANAGEMENT + EVENT_URL;
    private final EventServiceImpl eventService;

    @Autowired
    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = NO_AUTH_EVENT_URL + "/{id}")
    public ResponseEntity<Object> getEventById(@PathVariable UUID id) {
        try {
            return eventService.getEventById(id)
                    .<ResponseEntity<Object>>map(eventDTO -> ResponseEntity.ok().body(eventDTO))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }
    }

    @GetMapping(value = EVENT_MANAGEMENT_EVENT_URL)
    public ResponseEntity<List<EventDTO>> getAllAcceptedEvents() {
        try {
            List<EventDTO> events = eventService.getAllAcceptedEvents();
            return ResponseEntity.ok().body(events);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }


    @GetMapping(value = NO_AUTH_EVENT_URL + "/today")
    public ResponseEntity<List<EventTitleDTO>> getTodayEvents() {
        return ResponseEntity.ok().body(eventService.getTodayIncomingEvents());
    }

    @PostMapping(value = EVENT_MANAGEMENT_EVENT_URL)
    public ResponseEntity<Object> addNewEvent(@Valid @RequestBody ReceivedEventDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        } else {
            try {
                return ResponseEntity.ok().body(eventService.addEvent(dto));
            } catch (Error e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok().body("Event created successfully");
        }
    }

    @DeleteMapping(EVENT_MANAGEMENT_EVENT_URL + "/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable UUID id) {
        try {
            eventService.deleteEventById(id);
            return ResponseEntity.ok().body("Event deleted successfully");
        } catch (NoSuchElementException error) {
            return ResponseEntity.badRequest().body("An error occurred: " + error.getMessage());
        }
    }

    @PatchMapping(EVENT_MANAGEMENT_EVENT_URL + "/{id}")
    public ResponseEntity<Object> updateEvent2(@Valid @PathVariable UUID id, @RequestBody ReceivedEventDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        } else {
            try {
                eventService.updateEvent(dto, id);
            } catch (Error e) {
                return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
            }
            return ResponseEntity.ok().body("Event updated successfully");
        }
    }

    @PatchMapping(EVENT_MANAGEMENT_EVENT_URL)
    public ResponseEntity<Object> updateEvent(@RequestBody ReceivedEventDateDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        } else {
            try {
                eventService.updateEventData(dto);
            } catch (Error e) {
                return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
            }
            return ResponseEntity.ok().body("Event updated successfully");

        }
    }

}

