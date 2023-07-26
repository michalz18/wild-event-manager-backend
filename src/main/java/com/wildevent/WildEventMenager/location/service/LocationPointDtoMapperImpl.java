package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.LocationPointDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationPointDtoMapperImpl implements LocationPointDtoMapper {

    @Override
    public List<LocationPointDto> getLocationPointsDtoFromLocation(List<Location> locations) {
        return locations
                .stream()
                .map(this::getLocationPointDtoFromLocation)
                .toList();
    }

    private LocationPointDto getLocationPointDtoFromLocation(Location location) {
        return new LocationPointDto(
                location.getId(),
                location.getCoordinate().getCoordinateX(),
                location.getCoordinate().getCoordinateY());
    }

}
