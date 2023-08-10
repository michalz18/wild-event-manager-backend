package com.wildevent.WildEventMenager.event.service.dtoMappers;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventTitleDTOMapper {
    List<EventTitleDTO> getEventTitlesDTOFromEvent(List<Event> eventList);
}
