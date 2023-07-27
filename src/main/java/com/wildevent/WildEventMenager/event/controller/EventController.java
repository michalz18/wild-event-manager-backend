package com.wildevent.WildEventMenager.event.controller;

import com.wildevent.WildEventMenager.event.model.EventDTO;
import com.wildevent.WildEventMenager.event.model.EventTitleDTO;
import com.wildevent.WildEventMenager.event.service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventServiceImpl eventService;


    @Autowired
    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEventById(@PathVariable UUID id) {
        try {
            EventDTO event = eventService.getEventById(id);
            if (event != null) {
                return ResponseEntity.ok(event);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }
    }

    @GetMapping("/today")
    public ResponseEntity<List<EventTitleDTO>> getTodayEvents() {
        return ResponseEntity.ok().body(eventService.getTodayEvents());
    }
}

