package com.wildevent.WildEventMenager.event.controller;

import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDTO;
import com.wildevent.WildEventMenager.event.service.EventServiceImpl;
import com.wildevent.WildEventMenager.security.AccessUrlProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping(value = NO_AUTH_EVENT_URL + "/today")
    public ResponseEntity<List<EventTitleDTO>> getTodayEvents() {
        return ResponseEntity.ok().body(eventService.getTodayIncomingEvents());
    }

    @PostMapping(value = EVENT_MANAGEMENT_EVENT_URL)
    public ResponseEntity<Object> consumeForm(@Valid @RequestBody ReceivedEventDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        } else {
            //dodatkowe operacje na obiekcie dto
            // TODO
            return ResponseEntity.ok().build();
        }
    }

}

