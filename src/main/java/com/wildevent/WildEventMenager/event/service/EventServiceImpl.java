package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.dto.EventDTO;
import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import com.wildevent.WildEventMenager.event.repository.EventRepository;
import com.wildevent.WildEventMenager.event.service.dtoMappers.EventDTOMapper;
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
    private final EventDTOMapper eventDTOMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventTitleDTOMapper eventTitleDTOMapper, EventDTOMapper eventDTOMapper) {
        this.eventRepository = eventRepository;
        this.eventTitleDTOMapper = eventTitleDTOMapper;
        this.eventDTOMapper = eventDTOMapper;
    }

    @Override
    public Optional<EventDTO> getEventById(UUID id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        return eventOptional.map(eventDTOMapper::getEventDtoFromEvent);
    }

    @Override
    public List<EventTitleDTO> getTodayIncomingEvents() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endOfDay = now.withHour(23).withMinute(59).withSecond(59);
        List<Event> todayEvents = eventRepository.findAllByOpenToPublicIsTrueAndStartsAtBetweenOrderByStartsAtAsc(now, endOfDay);
        return eventTitleDTOMapper.getEventTitlesDTOFromEvent(todayEvents);
    }
}
