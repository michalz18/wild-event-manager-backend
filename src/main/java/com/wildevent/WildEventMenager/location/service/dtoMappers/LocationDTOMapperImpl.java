package com.wildevent.WildEventMenager.location.service.dtoMappers;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.LocationDTO;
import org.springframework.stereotype.Service;

@Service
public class LocationDTOMapperImpl implements LocationDTOMapper {
    @Override
    public LocationDTO mapToDTO(Location location) {
        return new LocationDTO(
                location.getId(),
                location.getTitle(),
                location.getDescription()
        );
    }
}
