package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.dto.EventDTO;

import java.util.Optional;
import java.util.UUID;
import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
  
    List<EventTitleDTO> getTodayIncomingEvents();
    Optional<EventDTO> getEventById(UUID id);

    UUID addEvent(ReceivedEventDTO dto);

    void deleteEventById(UUID id);

    List<Event> findAllEventsByOrganizer(UUID id);


    UUID updateEvent(ReceivedEventDTO dto, UUID id);

    void updateEventData(ReceivedEventDateDTO dto);
}
