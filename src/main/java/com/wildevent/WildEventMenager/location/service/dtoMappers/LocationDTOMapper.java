package com.wildevent.WildEventMenager.location.service.dtoMappers;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.dto.LocationDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationIdTitleDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationPointDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationDTOMapper {
    LocationDTO getLocationDtoFromLocation(Location location);

    LocationIdTitleDTO getLocationIdTitleDTO(Location location);

    List<LocationPointDTO> getLocationPointsDtoFromLocation(List<Location> locations);
}
