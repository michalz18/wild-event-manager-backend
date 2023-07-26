package com.wildevent.WildEventMenager.event.service;


import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.EventDTO;
import com.wildevent.WildEventMenager.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class EventServiceImp implements EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImp(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventDTO getEventById(UUID id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            return buildEventDTO(eventOptional.get());
        } else {
            throw new IllegalArgumentException("Event not found for id: " + id);
        }
    }

    private EventDTO buildEventDTO(Event event) {
        return new EventDTO(
                event.getTitle(),
                event.getDescription(),
                event.getStartsAt(),
                event.getEndsAt(),
                event.getLocation().getTitle());
    }
}
