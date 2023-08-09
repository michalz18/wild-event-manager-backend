package com.wildevent.WildEventMenager.location.service.dtoMappers;

import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import com.wildevent.WildEventMenager.event.service.dtoMappers.EventTitleDTOMapper;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.dto.LocationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationDTOMapperImpl implements LocationDTOMapper {

    private final EventTitleDTOMapper eventTitleDTOMapper;

    public LocationDTOMapperImpl(EventTitleDTOMapper eventTitleDTOMapper) {
        this.eventTitleDTOMapper = eventTitleDTOMapper;
    }

    @Override
    public LocationDTO getLocationDtoFromLocation(Location location) {
        List<EventTitleDTO> events = eventTitleDTOMapper.getEventTitlesDTOFromEvent(location.getEvents());
        return new LocationDTO(
                location.getId(),
                location.getTitle(),
                location.getDescription(),
                events
        );
    }
}
