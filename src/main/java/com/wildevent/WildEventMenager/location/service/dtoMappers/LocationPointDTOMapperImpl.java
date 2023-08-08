package com.wildevent.WildEventMenager.location.service.dtoMappers;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.LocationPointDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationPointDTOMapperImpl implements LocationPointDTOMapper {

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
