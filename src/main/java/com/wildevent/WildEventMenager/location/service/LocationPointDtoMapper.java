package com.wildevent.WildEventMenager.location.service;

import com.wildevent.WildEventMenager.location.model.Location;
import com.wildevent.WildEventMenager.location.model.LocationPointDto;

import java.util.List;

public interface LocationPointDtoMapper {
    List<LocationPointDto> getLocationPointsDtoFromLocation(List<Location> locations);
}
