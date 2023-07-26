package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.event.model.EventTitleDTO;
import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventTitleDTO> getTodayEvents() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endOfDay = now.withHour(23).withMinute(59).withSecond(59);
        List<Event> events = eventRepository.findAllByAcceptedTrueAndStartsAtBetween(now, endOfDay);

        return events.stream()
                .map(e -> new EventTitleDTO(e.getId(), e.getTitle(), e.getStartsAt(), e.getLocation().getTitle()))
                .collect(Collectors.toList());
    }
}
