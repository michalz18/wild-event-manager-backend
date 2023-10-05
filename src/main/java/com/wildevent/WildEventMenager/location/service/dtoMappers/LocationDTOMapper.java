package com.wildevent.WildEventMenager.location.service.dtoMappers;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.dto.*;
import com.wildevent.WildEventMenager.map.model.Map;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationDTOMapper {
    LocationDTO getLocationDtoFromLocation(Location location);

    LocationIdTitleDTO getLocationIdTitleDTO(Location location);

    List<LocationPointDTO> getLocationPointsDtoFromLocation(List<Location> locations);

    List<LocationWithCoordinateDTO> getLocationWithCoordinateDTO(List<Location> locations);

    Location getNewLocationFromDTO(ReceivedLocationDTO locationDTO, Map map);

    Location getUpdatedLocationFromReceivedDto(ReceivedLocationDTO locationDTO, Location location);
}
