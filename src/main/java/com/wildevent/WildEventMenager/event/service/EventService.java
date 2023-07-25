package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.dto.EventDTO;
import com.wildevent.WildEventMenager.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> getTodayEvents() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endOfDay = now.withHour(23).withMinute(59).withSecond(59);
        return eventRepository.findUpcomingEvents(now, endOfDay);
    }
}
