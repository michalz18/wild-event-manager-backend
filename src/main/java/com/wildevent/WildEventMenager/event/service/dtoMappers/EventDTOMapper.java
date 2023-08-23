package com.wildevent.WildEventMenager.event.service.dtoMappers;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.dto.EventDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDTO;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.user.model.WildUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventDTOMapper {
    EventDTO getEventDtoFromEvent(Event event);

    Event getEventFormReceivedEventDTO(ReceivedEventDTO receivedEventDTO, Location location, List<WildUser> organizerList);
}
