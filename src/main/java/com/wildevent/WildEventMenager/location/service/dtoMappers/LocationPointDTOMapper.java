package com.wildevent.WildEventMenager.location.service.dtoMappers;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.dto.LocationPointDTO;

import java.util.List;

public interface LocationPointDTOMapper {
    List<LocationPointDTO> getLocationPointsDtoFromLocation(List<Location> locations);
}
