package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.dto.EventDTO;
import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDTO;
import com.wildevent.WildEventMenager.event.repository.EventRepository;
import com.wildevent.WildEventMenager.event.service.dtoMappers.EventDTOMapper;
import com.wildevent.WildEventMenager.event.service.dtoMappers.EventTitleDTOMapper;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.repository.LocationRepository;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventTitleDTOMapper eventTitleDTOMapper;
    private final EventDTOMapper eventDTOMapper;
    private final LocationRepository locationRepository;
    private final WildUserRepository wildUserRepository;

    public EventServiceImpl(EventRepository eventRepository, EventTitleDTOMapper eventTitleDTOMapper, EventDTOMapper eventDTOMapper, LocationRepository locationRepository, WildUserRepository wildUserRepository) {
        this.eventRepository = eventRepository;
        this.eventTitleDTOMapper = eventTitleDTOMapper;
        this.eventDTOMapper = eventDTOMapper;
        this.locationRepository = locationRepository;
        this.wildUserRepository = wildUserRepository;
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

    public List<EventDTO> getAllAcceptedEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(eventDTOMapper::getEventDtoFromEvent).collect(Collectors.toList());

    }

    public void addEvent(ReceivedEventDTO dto) {
        Location location = locationRepository.findById(UUID.fromString(dto.getLocationId()))
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));

        List<WildUser> organizerList = dto.getOrganizers().stream()
                .map(el -> wildUserRepository.findById(UUID.fromString(el))
                        .orElseThrow(() -> new EntityNotFoundException("User not found")))
                .toList();

        eventRepository.save(eventDTOMapper.getEventFormReceivedEventDTO(dto, location, organizerList));
    }
}
