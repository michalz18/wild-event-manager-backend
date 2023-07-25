package com.wildevent.WildEventMenager.event.controller;

import com.wildevent.WildEventMenager.dto.EventDTO;
import com.wildevent.WildEventMenager.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/today")
    public List<EventDTO> getTodayEvents() {
        return eventService.getTodayEvents();
    }
}
