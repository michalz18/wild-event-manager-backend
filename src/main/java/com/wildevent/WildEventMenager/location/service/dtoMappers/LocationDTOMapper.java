package com.wildevent.WildEventMenager.location.service.dtoMappers;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.LocationDTO;
import org.springframework.stereotype.Service;

@Service
public interface LocationDTOMapper {
    LocationDTO mapToDTO(Location location);
}
