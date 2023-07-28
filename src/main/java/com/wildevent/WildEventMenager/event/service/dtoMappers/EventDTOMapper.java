package com.wildevent.WildEventMenager.event.service.dtoMappers;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.EventDTO;
import org.springframework.stereotype.Service;

@Service
public interface EventDTOMapper {
    EventDTO getEventDtoFromEvent(Event event);
}
