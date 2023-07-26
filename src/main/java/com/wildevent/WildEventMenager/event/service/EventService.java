package com.wildevent.WildEventMenager.event.service;

import com.wildevent.WildEventMenager.event.model.EventDTO;

import java.util.UUID;

public interface EventService {
    EventDTO getEventById(UUID id);
}
