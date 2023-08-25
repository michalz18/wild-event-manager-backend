package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.dto.EventDTO;

import java.util.Optional;
import java.util.UUID;
import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
  
    List<EventTitleDTO> getTodayIncomingEvents();
    Optional<EventDTO> getEventById(UUID id);
    List<Event> findAllEventsByOrganizer(UUID id);
}
