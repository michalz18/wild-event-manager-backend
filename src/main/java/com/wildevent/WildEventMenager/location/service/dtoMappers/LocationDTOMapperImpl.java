package com.wildevent.WildEventMenager.location.service.dtoMappers;

import com.wildevent.WildEventMenager.coordinate.service.CoordinateDTOMapper;
import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import com.wildevent.WildEventMenager.event.service.dtoMappers.EventDTOMapper;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.dto.LocationDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationIdTitleDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationPointDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationWithCoordinateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationDTOMapperImpl implements LocationDTOMapper {
    private final EventDTOMapper eventDTOMapper;

    private final CoordinateDTOMapper coordinateDTOMapper;

    public LocationDTOMapperImpl(EventDTOMapper eventDTOMapper, CoordinateDTOMapper coordinateDTOMapper) {
        this.eventDTOMapper = eventDTOMapper;
        this.coordinateDTOMapper = coordinateDTOMapper;
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
                location.getCoordinate().getLatitude(),
                location.getCoordinate().getLongitude());
    }

    @Override
    public List<LocationWithCoordinateDTO> getLocationWithCoordinateDTO(List<Location> locations) {
        return locations
                .stream()
                .map(location -> new LocationWithCoordinateDTO(
                    location.getId(),
                    location.getTitle(),
                    location.getDescription(),
                    coordinateDTOMapper.getCoordinateDTO((location.getCoordinate()))))
                .toList();
    }
}
