package com.wildevent.WildEventMenager.map.model.dto;

import com.wildevent.WildEventMenager.coordinate.model.CoordinateDTO;
import com.wildevent.WildEventMenager.location.model.dto.LocationPointDTO;

import java.util.List;
import java.util.UUID;

public record MapDTO(UUID id, CoordinateDTO coordinate, double zoom, int bearing, List<LocationPointDTO> locations) {

}
