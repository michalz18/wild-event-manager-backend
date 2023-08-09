package com.wildevent.WildEventMenager.event.service.dtoMappers;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.dto.EventDTO;
import org.springframework.stereotype.Service;

@Service
public class EventDTOMapperImpl implements EventDTOMapper {

    @Override
    public EventDTO getEventDtoFromEvent(Event event) {
        return new EventDTO(
                event.getTitle(),
                event.getDescription(),
                event.getStartsAt(),
                event.getEndsAt(),
                event.getLocation().getTitle());
    }
}
