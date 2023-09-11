package com.wildevent.WildEventMenager.event.service.dtoMappers;

import com.wildevent.WildEventMenager.event.model.Event;
import com.wildevent.WildEventMenager.event.model.dto.EventDTO;
import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDTO;
import com.wildevent.WildEventMenager.event.model.dto.ReceivedEventDateDTO;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.user.model.WildUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventDTOMapperImpl implements EventDTOMapper {
    @Override
    public EventDTO getEventDtoFromEvent(Event event) {
        return new EventDTO(
                event.getId(),
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

    @Override
    public Event getUpdatedEventFromReceivedEventDTO(Event event, ReceivedEventDTO receivedEventDTO, Location location, List<WildUser> organizerList) {
        event.setTitle(receivedEventDTO.getTitle());
        event.setDescription(receivedEventDTO.getDescription());
        event.setStartsAt(receivedEventDTO.getDateRange().getStartsAt());
        event.setEndsAt(receivedEventDTO.getDateRange().getEndsAt());
        event.setLocation(location);
        event.getOrganizer().clear();
        event.getOrganizer().addAll(organizerList);
        event.setOpenToPublic(receivedEventDTO.isOpenToPublic());
        return event;
    }

    @Override
    public List<EventTitleDTO> getEventTitlesDTOFromEvent(List<Event> eventList) {
        return eventList.stream()
                .map(this::getEventTitleDTOFromEvent)
                .collect(Collectors.toList());
    }

    @Override
    public Event getUpdatedEventFromReceivedDate(ReceivedEventDateDTO dto, Event event) {
        event.setStartsAt(dto.getDateRange().getStartsAt());
        event.setEndsAt(dto.getDateRange().getEndsAt());
        return event;
    }

    private EventTitleDTO getEventTitleDTOFromEvent(Event event) {
        return new EventTitleDTO(event.getId(), event.getTitle(), event.getStartsAt(), event.getLocation().getTitle());
    }

}
