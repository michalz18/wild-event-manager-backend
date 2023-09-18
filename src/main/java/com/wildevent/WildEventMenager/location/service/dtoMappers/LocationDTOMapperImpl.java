package com.wildevent.WildEventMenager.location.service.dtoMappers;

import com.wildevent.WildEventMenager.coordinate.model.Coordinate;
import com.wildevent.WildEventMenager.coordinate.service.CoordinateDTOMapper;
import com.wildevent.WildEventMenager.event.model.dto.EventTitleDTO;
import com.wildevent.WildEventMenager.event.service.dtoMappers.EventDTOMapper;
import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.dto.*;
import com.wildevent.WildEventMenager.map.model.Map;
import com.wildevent.WildEventMenager.user.model.WildUserDTO;
import com.wildevent.WildEventMenager.user.service.dtoMapper.UserDTOMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationDTOMapperImpl implements LocationDTOMapper {
    private final EventDTOMapper eventDTOMapper;

    private final CoordinateDTOMapper coordinateDTOMapper;

    private final UserDTOMapper userDTOMapper;

    public LocationDTOMapperImpl(EventDTOMapper eventDTOMapper, CoordinateDTOMapper coordinateDTOMapper, UserDTOMapper userDTOMapper) {
        this.eventDTOMapper = eventDTOMapper;
        this.coordinateDTOMapper = coordinateDTOMapper;
        this.userDTOMapper = userDTOMapper;
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
                .map(this::getLocationDTO)
                .toList();
    }

    @Override
    public Location getNewLocationFromDTO(ReceivedLocationDTO locationDTO, Map map) {
        Coordinate coordinate = new Coordinate(locationDTO.getLongitude(), locationDTO.getLatitude());
        return new Location(
                locationDTO.getTitle(), locationDTO.getDescription(), coordinate, new ArrayList<>(), map);
    }

    @Override
    public Location getUpdatedLocationFromReceivedDto(ReceivedLocationDTO locationDTO, Location location) {
        location.setTitle(locationDTO.getTitle());
        location.setDescription(locationDTO.getDescription());
        location.setCoordinate(new Coordinate(locationDTO.getLongitude(), locationDTO.getLatitude()));
        return location;
    }

    private LocationWithCoordinateDTO getLocationDTO(Location location) {
        return new LocationWithCoordinateDTO(
                location.getId(),
                location.getTitle(),
                location.getDescription(),
                coordinateDTOMapper.getCoordinateDTO((location.getCoordinate())),
                getKeepers(location));
    }

    private List<WildUserDTO> getKeepers(Location location) {
        return location.getWildUser().stream().map(userDTOMapper::getUserDtoFromWildUser).toList();
    }
}
