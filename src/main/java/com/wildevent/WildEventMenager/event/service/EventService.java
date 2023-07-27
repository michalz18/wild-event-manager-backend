package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.event.model.EventDTO;
import java.util.UUID;
import com.wildevent.WildEventMenager.event.model.EventTitleDTO;
import java.util.List;

public interface EventService {
  
    List<EventTitleDTO> getTodayEvents();
    EventDTO getEventById(UUID id);
}
