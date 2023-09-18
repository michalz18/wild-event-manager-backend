package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.dto.EventDTO;
import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDateDTO;
import com.wildevent.WildEventMenager.event.repository.EventRepository;
import com.wildevent.WildEventMenager.event.service.dtoMappers.EventDTOMapper;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.repository.LocationRepository;
import com.wildevent.WildEventMenager.user.model.WildUser;
import com.wildevent.WildEventMenager.user.repository.WildUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventDTOMapper eventDTOMapper;
    private final LocationRepository locationRepository;
    private final WildUserRepository wildUserRepository;

    public EventServiceImpl(EventRepository eventRepository, EventDTOMapper eventDTOMapper, LocationRepository locationRepository, WildUserRepository wildUserRepository) {
        this.eventRepository = eventRepository;
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
        return eventDTOMapper.getEventTitlesDTOFromEvent(todayEvents);
    }

    public List<EventDTO> getAllAcceptedEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(eventDTOMapper::getEventDtoFromEvent).collect(Collectors.toList());

    }

    @Override
    public UUID addEvent(ReceivedEventDTO dto) {
        Event newEvent = eventRepository.save(
                eventDTOMapper.getEventFormReceivedEventDTO(
                        dto,
                        extractLocationFromDTO(dto),
                        extractWildUserFromDTO(dto)));
        return newEvent.getId();
    }

    private List<WildUser> extractWildUserFromDTO(ReceivedEventDTO dto) {
        return dto.getOrganizers().stream()
                .map(el -> wildUserRepository.findById(UUID.fromString(el))
                        .orElseThrow(() -> new EntityNotFoundException("User not found")))
                .toList();
    }

    private Location extractLocationFromDTO(ReceivedEventDTO dto) {
        return locationRepository.findById(UUID.fromString(dto.getLocationId()))
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));
    }

    @Override
    public void deleteEventById(UUID id) {
        if (!eventRepository.existsById(id)) {
            throw new NoSuchElementException("Event not found");
        } else {
            eventRepository.deleteById(id);
        }
    }

    @Override
    public List<Event> findAllEventsByOrganizer(UUID userId) {
        return eventRepository.findAll()
                .stream()
                .filter(event -> event.getOrganizer().stream().anyMatch(user -> user.getId().equals(userId)))
                .toList();
    }


    @Override
    public UUID updateEvent(ReceivedEventDTO dto, UUID id) {
        Optional<Event> getEvent = eventRepository.findById(id);
        getEvent.ifPresent(event -> eventRepository.save(
                eventDTOMapper.getUpdatedEventFromReceivedEventDTO(
                        event,
                        dto,
                        extractLocationFromDTO(dto),
                        extractWildUserFromDTO(dto))));
        return getEvent.orElseThrow(() -> new NoSuchElementException("Event not found!")).getId();
    }

    @Override
    public void updateEventData(ReceivedEventDateDTO dto) {
        Optional<Event> getEvent = eventRepository.findById(UUID.fromString(dto.getId()));
        getEvent.ifPresent(event -> eventRepository.save(
                eventDTOMapper.getUpdatedEventFromReceivedDate(
                        dto,
                        event
                )));
    }
}
