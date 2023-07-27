package com.wildevent.WildEventMenager.event.controller;

import com.wildevent.WildEventMenager.event.model.EventTitleDTO;
import com.wildevent.WildEventMenager.event.service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventServiceImpl eventService;

    @Autowired
    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/today")
    public ResponseEntity<List<EventTitleDTO>> getTodayEvents() {
        return ResponseEntity.ok().body(eventService.getTodayEvents());
    }
}
