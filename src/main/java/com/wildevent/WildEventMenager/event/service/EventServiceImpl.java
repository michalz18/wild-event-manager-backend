package com.wildevent.WildEventMenager.event.service;


import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.EventDTO;
import com.wildevent.WildEventMenager.event.model.EventTitleDTO;
import com.wildevent.WildEventMenager.event.repository.EventRepository;
import com.wildevent.WildEventMenager.event.service.dtoMappers.EventTitleDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventTitleDTOMapper eventTitleDTOMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventTitleDTOMapper eventTitleDTOMapper) {
        this.eventRepository = eventRepository;
        this.eventTitleDTOMapper = eventTitleDTOMapper;
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

    @Override
    public List<EventTitleDTO> getTodayEvents() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endOfDay = now.withHour(23).withMinute(59).withSecond(59);
        List<Event> events = eventRepository.findAllByAcceptedTrueAndStartsAtBetween(now, endOfDay);

        return eventTitleDTOMapper.getEventTitlesDTOFromEvent(events);
    }
}
