package com.wildevent.WildEventMenager.event.service.dtoMappers;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.dto.EventDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDTO;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.user.model.WildUser;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Event getEventFormReceivedEventDTO(ReceivedEventDTO receivedEventDTO, Location location, List<WildUser> organizerList) {
        return new Event(
                receivedEventDTO.getTitle(),
                receivedEventDTO.getDescription(),
                receivedEventDTO.getDateRange().getStartsAt(),
                receivedEventDTO.getDateRange().getEndsAt(),
                location,
                receivedEventDTO.isOpenToPublic(),
                organizerList
        );
    }
}
