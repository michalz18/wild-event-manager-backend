package com.wildevent.WildEventMenager.location.service.dtoMappers;

import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import com.wildevent.WildEventMenager.event.service.dtoMappers.EventDTOMapper;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.dto.LocationDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationIdTitleDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationPointDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationDTOMapperImpl implements LocationDTOMapper {
    private final EventDTOMapper eventDTOMapper;

    public LocationDTOMapperImpl(EventDTOMapper eventDTOMapper) {
        this.eventDTOMapper = eventDTOMapper;
    }

    @Override
    public LocationDTO getLocationDtoFromLocation(Location location) {
        List<EventTitleDTO> events = eventDTOMapper.getEventTitlesDTOFromEvent(location.getEvents());
        return new LocationDTO(
                location.getId(),
                location.getTitle(),
                location.getDescription(),
                events
        );
    }

    @Override
    public LocationIdTitleDTO getLocationIdTitleDTO(Location location) {
        return new LocationIdTitleDTO(
                location.getId(),
                location.getTitle()
        );
    }

    @Override
    public List<LocationPointDTO> getLocationPointsDtoFromLocation(List<Location> locations) {
        return locations
                .stream()
                .map(this::getLocationPointDtoFromLocation)
                .toList();
    }

    private LocationPointDTO getLocationPointDtoFromLocation(Location location) {
        return new LocationPointDTO(
                location.getId(),
                location.getCoordinate().getCoordinateX(),
                location.getCoordinate().getCoordinateY());
    }
}
