package com.wildevent.WildEventMenager.event.service.dtoMappers;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.EventTitleDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventTitleDTOMapperImpl implements EventTitleDTOMapper {
    @Override
    public List<EventTitleDTO> getEventTitlesDTOFromEvent(List<Event> eventList) {
        return eventList.stream()
                .map(this::getEventTitleDTOFromEvent)
                .collect(Collectors.toList());
    }

    private EventTitleDTO getEventTitleDTOFromEvent(Event event) {
        return new EventTitleDTO(event.getId(), event.getTitle(), event.getStartsAt(), event.getLocation().getTitle());

    }
}
